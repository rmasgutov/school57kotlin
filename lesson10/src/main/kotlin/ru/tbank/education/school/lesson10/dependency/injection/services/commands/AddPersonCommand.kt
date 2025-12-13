package ru.tbank.education.school.lesson10.dependency.injection.services.commands

import ru.tbank.education.school.lesson10.dependency.injection.annotations.Autowired
import ru.tbank.education.school.lesson10.dependency.injection.model.Person
import ru.tbank.education.school.lesson10.dependency.injection.services.PersonStore

/**
 * Команда для добавления нового пользователя в хранилище.
 *
 * Демонстрирует:
 * - Использование @CommandDefinition для метаданных команды
 * - Автоматическое внедрение зависимостей через @Autowired
 * - Реализацию паттерна Command
 *
 * Команда принимает 2 аргумента: имя пользователя и email.
 */
@CommandDefinition(
    name = "add",
    arguments = "<person name> <email>",
    description = "Create and add a person with given name and email to the person store",
    aliases = ["Add", "New", "new"],
    argsNumber = 2
)
class AddPersonCommand : Command {

    /**
     * PersonStore будет автоматически внедрён через Reflection API
     * благодаря аннотации @Autowired.
     */
    @Autowired
    private lateinit var personStore: PersonStore

    /**
     * Выполняет команду добавления пользователя.
     * args[0] - имя пользователя
     * args[1] - email пользователя
     */
    override fun execute(vararg args: String) {
        personStore.save(Person(args[0], args[1]))
        println("Person was successfully added!")
    }
}
