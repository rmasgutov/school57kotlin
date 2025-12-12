package ru.tbank.education.school.lesson10.dependency.injection.di

import ru.tbank.education.school.lesson10.dependency.injection.annotations.Autowired
import ru.tbank.education.school.lesson10.dependency.injection.annotations.Component
import ru.tbank.education.school.lesson10.dependency.injection.services.commands.Command
import ru.tbank.education.school.lesson10.dependency.injection.services.commands.CommandDefinition
import ru.tbank.education.school.lesson10.dependency.injection.services.commands.CommandHandler
import java.io.File
import java.nio.file.FileSystems
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

/**
 * Простая реализация Dependency Injection контейнера на Kotlin Reflection.
 *
 * Особенности:
 * - Пакет сканируется на наличие компонентов с аннотацией @Component.
 * - Для каждого компонента создаётся singleton-экземпляр.
 * - Инъекция зависимостей осуществляется через параметры конструктора.
 * - Конструктор может включать параметры с аннотацией @Autowired.
 * - Идентификация зависимостей происходит по типу.
 *
 */
class KotlinInjectionFactory(private val basePackage: String) {

    /** Карта "класс → созданный singleton" */
    private val singletons = mutableMapOf<KClass<*>, Any>()

    /**
     * Основной метод инициализации DI-контейнера:
     * 1. Находит компоненты
     * 2. Создаёт их экземпляры
     * 3. Внедряет зависимости через конструктор
     */
    fun initialize() {
        val componentClasses = scanForComponents()
        instantiateSingletons(componentClasses)
    }

    /**
     * Возвращает экземпляр бина по классу.
     */
    fun <T : Any> getBean(kClass: KClass<T>): T? {
        @Suppress("UNCHECKED_CAST")
        return singletons[kClass] as? T
    }

    /**
     * Внедряет зависимости в существующий объект через Kotlin Reflection.
     * Использует те же правила, что и DI при создании newInstance.
     */
    fun <T : Any> injectMembers(obj: T): T {
        val kClass = obj::class
        println("DEBUG: Injecting members into ${kClass.simpleName}")

        // Для lateinit var нужно работать через javaField напрямую
        for (prop in kClass.members) {
            // Пропускаем не-свойства
            if (prop !is KProperty<*>) continue
            println("DEBUG: Checking property ${prop.name}")

            // Получаем Java field
            val javaField = prop.javaField
            if (javaField == null) {
                println("DEBUG: No javaField for ${prop.name}")
                continue
            }

            // Проверяем аннотацию на Java field, а не на Kotlin property
            if (!javaField.isAnnotationPresent(Autowired::class.java)) {
                println("DEBUG: Field ${javaField.name} has no @Autowired")
                continue
            }

            println("DEBUG: Field ${javaField.name} has @Autowired")

            val dependencyType = prop.returnType.classifier as? KClass<*> ?: continue
            println("DEBUG: Dependency type: ${dependencyType.simpleName}")

            val dependency = findBeanByType(dependencyType)
                ?: createInstance(dependencyType).also { singletons[dependencyType] = it }

            println("DEBUG: Found/created dependency: ${dependency::class.simpleName}")

            javaField.isAccessible = true
            javaField.set(obj, dependency)
            println("DEBUG: Successfully injected ${dependencyType.simpleName} into ${javaField.name}")
        }

        return obj
    }

    /**
     * Ищет классы, помеченные аннотацией @Component в указанном пакете.
     */
    fun scanForComponents(): List<KClass<*>> {
        val result = mutableListOf<KClass<*>>()

        for (file in scanPackage(basePackage)) {
            if (file.name.endsWith(".class")) {
                val className = getClassName(file)
                val clazz = Class.forName(className).kotlin

                if (clazz.hasAnnotation<Component>()) {
                    result += clazz
                }
            }
        }

        return result
    }

    /**
     * Создаёт singleton-экземпляры всех найденных компонентов.
     */
    fun instantiateSingletons(classes: List<KClass<*>>) {
        for (clazz in classes) {
            if (singletons.containsKey(clazz)) continue

            val instance = createInstance(clazz)
            singletons[clazz] = instance
        }
    }

    /**
     * Создаёт экземпляр компонента, внедряя зависимости через constructor injection.
     *
     * Kotlin-способ:
     * - Берём primaryConstructor
     * - Собираем map параметров → значений
     * - Вызываем callBy()
     */
    fun createInstance(clazz: KClass<*>): Any {
        val constructor = clazz.primaryConstructor
            ?: throw IllegalStateException("Component ${clazz.simpleName} must have a primary constructor")

        constructor.isAccessible = true

        // Если конструктор без параметров - просто вызываем
        if (constructor.parameters.isEmpty()) {
            return constructor.call()
        }

        // Собираем зависимости по параметрам конструктора
        val args = constructor.parameters.associateWith { param ->
            val type = param.type.classifier as? KClass<*>
                ?: throw IllegalStateException("Unsupported parameter type in ${clazz.simpleName}")

            // Ищем уже созданный объект подходящего типа
            val existing = findBeanByType(type)
            if (existing != null) {
                return@associateWith existing
            }

            // Автовнедрение: создаём недостающий бин, но только если он помечен @Component
            if (!type.hasAnnotation<Component>()) {
                throw IllegalStateException(
                    "Cannot auto-create dependency of type ${type.simpleName} for ${clazz.simpleName}. " +
                        "Dependency must be annotated with @Component or already registered."
                )
            }

            createInstance(type).also {
                singletons[type] = it
            }
        }

        return constructor.callBy(args)
    }

    /**
     * Ищет singleton по типу.
     */
    fun findBeanByType(type: KClass<*>): Any? {
        return singletons.entries.firstOrNull { (k, _) ->
            type.java.isAssignableFrom(k.java)
        }?.value
    }

    /**
     * Ищет классы-команды, создаёт их экземпляры и регистрирует в CommandHandler.
     *
     * Класс команды должен иметь:
     *  - @CommandDefinition аннотацию
     *  - реализовывать интерфейс Command
     *  - иметь primary constructor, совместимый с DI
     *
     * Примечание: HelpCommand создаётся вручную в CommandHandler и не должен регистрироваться здесь.
     */
    fun registerCommands() {
        val commandClasses = scanForAnnotatedClasses<CommandDefinition>()

        // Ищем CommandHandler среди созданных бинов
        val handler = singletons.entries
            .firstOrNull { CommandHandler::class.java.isAssignableFrom(it.key.java) }
            ?.value as? CommandHandler
            ?: throw IllegalStateException("CommandHandler bean not found")

        for (clazz in commandClasses) {
            // Пропускаем вложенные классы CommandHandler (например, HelpCommand)
            // Они создаются вручную внутри CommandHandler
            if (clazz.java.declaringClass == CommandHandler::class.java) {
                continue
            }

            // Создаём экземпляр команды
            val instance = createInstance(clazz)

            // ВАЖНО: Внедряем зависимости через @Autowired поля
            injectMembers(instance)

            singletons[clazz] = instance // кэшируем, вдруг пригодится

            handler.registerCommand(instance as Command)
        }
    }

    /**
     * Универсальный поиск классов с указанной аннотацией.
     *
     * Пример: scanForAnnotatedClasses<CommandDefinition>()
     */
    private inline fun <reified A : Annotation> scanForAnnotatedClasses(): List<KClass<*>> {
        val result = mutableListOf<KClass<*>>()

        for (file in scanPackage(basePackage)) {
            if (!file.name.endsWith(".class")) continue

            val className = getClassName(file)
            val kClass = Class.forName(className).kotlin

            if (kClass.hasAnnotation<A>()) {
                result += kClass
            }
        }

        return result
    }

    /**
     * Сканирует директорию и ищет .class файлы.
     * Это учебная демонстрация — в проде использовался бы ClassGraph или Reflections.
     */
    fun scanPackage(pkg: String): List<File> {
        val path = pkg.replace(".", FileSystems.getDefault().separator)
        val classLoader = Thread.currentThread().contextClassLoader
        val resources = classLoader.getResources(path)

        val files = mutableListOf<File>()

        resources.toList().forEach { url ->
            val dir = File(url.toURI())
            scanDirectory(dir, files)
        }

        return files
    }

    private fun scanDirectory(dir: File, out: MutableList<File>) {
        dir.listFiles()?.forEach { file ->
            if (file.isDirectory) {
                scanDirectory(file, out)
            } else {
                out += file
            }
        }
    }

    /**
     * Превращает путь к .class файлу в имя класса.
     */
    fun getClassName(file: File): String {
        val abs = file.absolutePath
        val sep = FileSystems.getDefault().separator
        val pkgPath = abs.substring(abs.indexOf(basePackage.replace(".", sep)))
        return pkgPath.replace(sep, ".").removeSuffix(".class")
    }
}
