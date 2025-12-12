package ru.tbank.education.school.lesson10.dependency.injection.services.commands

import ru.tbank.education.school.lesson10.dependency.injection.annotations.Autowired
import ru.tbank.education.school.lesson10.dependency.injection.services.PersonStore

/**
 * Команда для вывода списка всех пользователей из хранилища.
 *
 * Демонстрирует простую команду без аргументов,
 * использующую внедрение зависимостей.
 */
@CommandDefinition(
    name = "list",
    description = "List all persons added to the store"
)
class ListPersonsCommand : Command {
    /**
     * PersonStore внедряется автоматически через @Autowired.
     */
    @Autowired
    private lateinit var personStore: PersonStore

    /**
     * Выполняет команду вывода всех пользователей.
     * Аргументы не требуются (argsNumber = 0 по умолчанию).
     */
    override fun execute(vararg args: String) {
        println(personStore.getPersons())
    }
}
