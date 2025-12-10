package ru.tbank.education.school.lesson10.basics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson10.basics.runtime.MyRuntimeAnnotation
import ru.tbank.education.school.lesson10.basics.runtime.Person
import kotlin.reflect.KType
import kotlin.reflect.full.IllegalCallableAccessException
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.javaMethod
import kotlin.reflect.typeOf
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse

class SampleTest {

    /**
     * Тест демонстрирует создание объекта через KOTLIN Reflection API.
     *
     * Основные концепции:
     * - Person::class — получение KClass объекта
     * - primaryConstructor — доступ к первичному конструктору
     * - call() — вызов конструктора
     *
     * Важно: в Kotlin мы не используем Class.forName() и newInstance(),
     * потому что у языка есть собственная рефлексия.
     */
    @Test
    fun createObject() {
        // Получаем KClass объекта Person
        val kClass = Person::class

        // Получаем primary constructor
        val constructor = kClass.primaryConstructor!!

        // Создаём объект через Kotlin Reflection
        val person = constructor.call("Aleks", "Korotkov", 30)

        println(person)

        assertEquals("Aleks", person.name)
    }

    /**
     * Тест показывает получение информации о классе через Kotlin Reflection API.
     *
     * Демонстрируются:
     * - memberProperties — свойства Kotlin (name, surname, age)
     * - memberFunctions — функции (equals, toString, copy, componentN)
     * - primaryConstructor — параметры конструктора
     * - findAnnotation — чтение аннотаций Kotlin
     *
     * Важно: Kotlin-properties ≠ Java-fields.
     */
    @Test
    fun listMetadata() {
        val kClass = Person::class

        println("СВОЙСТВА:")
        for (prop in kClass.memberProperties) {
            println("Property: ${prop.name}, type=${prop.returnType}")
        }

        println("\nФУНКЦИИ:")
        for (func in kClass.memberFunctions) {
            println("Function: ${func.name}")
        }

        println("\nПАРАМЕТРЫ primary constructor:")
        for (param in kClass.primaryConstructor!!.parameters) {
            println("Parameter: $param")
        }

        println("\nАННОТАЦИИ:")
        val annotation = kClass.findAnnotation<MyRuntimeAnnotation>()
        println(annotation?.metainfo)

        assertEquals("Person metainformation", annotation?.metainfo)
    }

    /**
     * Тест показывает вызов метода через Kotlin Reflection API.
     *
     * Основные шаги:
     * - получаем список функций класса — memberFunctions
     * - находим нужную по имени
     * - вызываем её через call()
     *
     * В данном примере мы вызываем метод toString().
     */
    @Test
    fun invokeMethod() {
        val person = Person("Aleks", "Korotkov", 30)

        // Находим метод toString
        val toStringMethod = Person::class.memberFunctions
            .first { it.name == "toString" }

        // Вызываем метод
        val result = toStringMethod.call(person)

        println(result)

        assert(result is String)
    }

    /**
     * Тест демонстрирует вызов приватного метода через Kotlin Reflection.
     *
     * Важно:
     * - Приватные функции можно найти через declaredFunctions
     * - Обойти модификатор доступа напрямую нельзя
     * - Но можно получить Java Method и вызвать trySetAccessible()
     *
     * Таким образом, Kotlin Reflection работает с приватностью
     * только через "мост" к Java Reflection API.
     */
    @Test
    fun invokePrivateMethod() {
        val person = Person("Aleks", "Korotkov", 30)

        // Приватная функция sayHelloTo()
        val privateFunc = Person::class.declaredFunctions
            .first { it.name == "sayHelloTo" }

        // Попытка вызвать напрямую — приводит к IllegalCallableAccessException
        assertFailsWith<IllegalCallableAccessException> {
            privateFunc.call(person, "Maxim")
        }

        // Берём Java-метод и делаем его доступным
        val javaMethod = privateFunc.javaMethod!!
        javaMethod.trySetAccessible()

        // Теперь метод можно вызвать
        val result = javaMethod.invoke(person, "Oleg")

        println(result)

        assertEquals("Hello, Oleg! I'm Aleks Korotkov.", result)
    }

    /**
     * Тест демонстрирует чтение аннотаций в Kotlin Reflection.
     *
     * Основные концепции:
     * - findAnnotation<T>() — Kotlin-способ получить аннотацию
     * - annotations — список всех аннотаций
     *
     * Важно: аннотация должна иметь AnnotationRetention.RUNTIME,
     * чтобы быть доступной во время выполнения.
     */
    @Test
    fun annotationTest() {
        val annotation = Person::class.findAnnotation<MyRuntimeAnnotation>()

        println("Метаданные: ${annotation?.metainfo}")

        assertEquals("Person metainformation", annotation?.metainfo)
    }

    /**
     * Тест демонстрирует работу с generic-типами через Kotlin Reflection.
     *
     * Основные концепции:
     * - KType — отражение типа в Kotlin со сведениями о nullability и аргументах типа
     * - returnType у свойств позволяет получить полный тип (если он известен на этапе компиляции)
     * - type.arguments — список аргументов generic-типа (List<T>, Box<T> и т.д.)
     *
     * Важно: Kotlin, как и Java, использует type erasure.
     * Поэтому при работе с объектами информация о T часто теряется.
     * Однако декларативные типы (в свойствах, параметрах) сохраняют generic-структуру.
     */
    @Test
    fun genericTypeInfo() {
        // Класс с generic-полем
        data class Box<T>(val value: T)

        // Создаём экземпляр Box<String>
        val box = Box("Hello Kotlin")

        // Получаем метакласс
        val kClass = box::class

        // Находим свойство "value"
        val prop = kClass.memberProperties.first { it.name == "value" }

        // Получаем KType свойства
        val type = prop.returnType

        println("Тип свойства value = $type")
        println("Аргументы типа: ${type.arguments}")

        // Проверка: erased тип всегда String? (в данном случае — да)
        assertNotEquals(String::class, type.classifier)

        // Но generics runtime недоступны: это не Box<String>, а просто Box<out T>
        assertFalse(type.arguments.isNotEmpty())
    }

    /**
     * Тест демонстрирует использование reified-параметров и функции typeOf<T>()
     * для получения полного типа с generic-аргументами во время выполнения.
     *
     * Основные концепции:
     * - reified T в inline-функциях позволяет обращаться к T::class и typeOf<T>()
     * - typeOf<T>() возвращает KType, включая параметры типа (например, List<String>)
     * - KType сравнивает generic-структуру, поэтому List<String> != List<Int>
     *
     * Важно: без reified-параметра тип T будет стёрт,
     * и полноценную информацию о generic-типа получить невозможно.
     */
    @Test
    fun reifiedTypeOfTest() {
        // Получаем KType для конкретного generic-типа
        val listType = extractType<List<String>>()

        println("KType: $listType")
        println("Аргументы типа: ${listType.arguments}")

        // Проверяем, что аргумент типа — String
        val arg = listType.arguments.first().type
        assertEquals(String::class, arg?.classifier)

        // А это — другой тип, и они не равны
        val intListType = extractType<List<Int>>()
        assertNotEquals(listType, intListType)
    }

    // Вспомогательная inline-функция, позволяющая получить KType
    inline fun <reified T> extractType(): KType {
        return typeOf<T>()
    }

    /**
     * Тест демонстрирует эффект type erasure в Kotlin (и JVM в целом).
     *
     * Основные концепции:
     * - generic-параметры стираются во время выполнения
     * - listOf("a") и listOf(1) имеют один и тот же runtime-класс — ArrayList
     *
     * Важно: именно из-за стирания типов получить различие между List<String> и List<Int>
     * через обычный ::class невозможно — требуется reified и typeOf<T>().
     */
    @Test
    fun erasedGenericClassTest() {
        val list1 = listOf("a", "b")
        val list2 = listOf(1, 2)

        // В рантайме оба — просто ArrayList
        assertEquals(list1::class, list2::class)

        println("list1 class: ${list1::class}")
        println("list2 class: ${list2::class}")
    }
}
