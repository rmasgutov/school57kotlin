package ru.tbank.education.school.lesson10.dependency.injection

import ru.tbank.education.school.lesson10.dependency.injection.annotations.Autowired
import ru.tbank.education.school.lesson10.dependency.injection.di.KotlinInjectionFactory
import ru.tbank.education.school.lesson10.dependency.injection.model.Message
import ru.tbank.education.school.lesson10.dependency.injection.services.MessageService
import ru.tbank.education.school.lesson10.dependency.injection.services.PersonStore
import ru.tbank.education.school.lesson10.dependency.injection.services.commands.CommandHandler
import java.util.*

/**
 * Точка входа в приложение.
 *
 * Последовательность инициализации DI-контейнера:
 * 1. instantiateComponents() - находит и создаёт все @Component
 * 2. initializeComponents() - внедряет @Autowired зависимости во все компоненты
 * 3. initialize(application) - внедряет зависимости в сам Application
 * 4. registerCommands() - находит и регистрирует все @CommandDefinition команды
 * 5. run() - запускает основной цикл приложения
 */
fun main() {
    // Создаём объект приложения вручную
    val application = Application()

    // Создаём DI-контейнер
    val factory = KotlinInjectionFactory(application::class.java.packageName)

    // DI сам:
    // 1) найдёт компоненты
    // 2) создаст singleton-экземпляры
    // 3) внедрит их зависимости
    factory.initialize()

    // Дополнительно: внедряем зависимости в Application (оно не @Component)
    // В новой версии DI можно просто вызвать конструктор-инъекцию вручную
    val appWithDeps = factory.injectMembers(application)

    // Регистрируем команды (если сохраняем твой механизм)
    // — мы можем использовать старую логику или переписать под Kotlin
    factory.registerCommands()

    // Запуск
    appWithDeps.run()
}

/**
 * Главный класс приложения - простой сервис отправки сообщений.
 *
 * Демонстрирует полный цикл работы с собственным DI-контейнером:
 * - Автоматическое внедрение зависимостей через @Autowired
 * - Работу с командами через CommandHandler
 * - Взаимодействие с сервисами через интерфейсы
 */
class Application {

    /**
     * Scanner для чтения пользовательского ввода.
     */
    private val scanner = Scanner(System.`in`)

    /**
     * Сервис отправки сообщений.
     * Будет автоматически внедрён InjectionFactory.
     */
    @Autowired
    private lateinit var messageService: MessageService

    /**
     * Хранилище пользователей.
     * Будет автоматически внедрён InjectionFactory.
     */
    @Autowired
    private lateinit var personStore: PersonStore

    /**
     * Обработчик команд.
     * Будет автоматически внедрён InjectionFactory.
     */
    @Autowired
    private lateinit var commandHandler: CommandHandler

    /**
     * Основной цикл приложения.
     *
     * Принимает команды от пользователя и передаёт их CommandHandler
     * до тех пор, пока пользователь не введёт "exit".
     */
    fun run() {
        println(
            """
            Welcome! It's a simple push service. Type 'help' to get available commands. Type 'exit' to finish.
            """.trimIndent()
        )

        var input: String? = null
        while (!"exit".equals(input, ignoreCase = true)) {
            input = scanner.nextLine().trim().lowercase()
            commandHandler.executeCommand(input)
        }
        scanner.close()
    }

    /**
     * Метод для отправки push-уведомления пользователю.
     * (В текущей версии не используется, но может быть расширен)
     *
     * Демонстрирует работу с внедрёнными зависимостями.
     *
     * @param args массив аргументов: args[0] - имя пользователя
     */
    private fun sendPush(args: Array<String>) {
        if (args.size != 1) {
            throw IllegalArgumentException("message command should have 1 argument: <person name>")
        }

        val name = args[0]
        val person = personStore.getPerson(name)
            ?: throw RuntimeException("Person not found with name: $name")

        println("Input the message:")
        val message = scanner.nextLine().trim().lowercase()

        if (message.isEmpty()) {
            throw IllegalArgumentException("Message should not be empty!")
        }

        messageService.sendMessage(person, Message(message))
    }
}
