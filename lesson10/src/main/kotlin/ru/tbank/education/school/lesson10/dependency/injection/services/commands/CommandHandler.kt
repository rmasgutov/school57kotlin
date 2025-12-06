package ru.tbank.education.school.lesson10.dependency.injection.services.commands

import ru.tbank.education.school.lesson10.dependency.injection.annotations.Component

/**
 * Обработчик команд (Command Handler).
 *
 * Центральный компонент для управления командами:
 * - Регистрирует команды и их псевдонимы
 * - Выполняет команды по имени
 * - Валидирует количество аргументов
 * - Предоставляет справку (команда help)
 *
 * Помечен @Component, поэтому автоматически регистрируется в DI-контейнере.
 */
@Component
class CommandHandler {

    /**
     * Хранилище команд: ключ - имя команды или псевдоним, значение - экземпляр команды.
     * Позволяет быстро находить команду по имени.
     */
    private val commands = mutableMapOf<String, Command>()

    init {
        // Регистрируем встроенную команду help при создании обработчика
        val helpCommandInstance = HelpCommand(commands)
        registerCommand(helpCommandInstance)
    }

    /**
     * Регистрирует команду в обработчике.
     *
     * Метод публичный для упрощения. По сути, можно его перевести в package-private
     * и рядом написать отдельный бин для загрузки команд.
     *
     * Процесс регистрации:
     * 1. Получает аннотацию @CommandDefinition через Reflection API
     * 2. Регистрирует команду по основному имени
     * 3. Регистрирует команду по всем псевдонимам
     *
     * @param command команда для регистрации
     */
    fun registerCommand(command: Command) {
        val definition = command.javaClass.getAnnotation(CommandDefinition::class.java)
        val name = definition.name
        commands[name] = command

        // Регистрируем все псевдонимы команды
        for (alias in definition.aliases) {
            commands[alias] = command
        }
    }

    /**
     * Выполняет команду на основе пользовательского ввода.
     *
     * Процесс выполнения:
     * 1. Парсит ввод: первое слово - имя команды, остальное - аргументы
     * 2. Проверяет существование команды
     * 3. Валидирует количество аргументов через аннотацию
     * 4. Выполняет команду
     *
     * @param input строка ввода пользователя
     */
    fun executeCommand(input: String) {
        // Разбиваем ввод на части: первая часть - имя команды, остальное - аргументы
        var args = input.split(" ").toTypedArray()
        if (args.isEmpty()) {
            return
        }

        val name = args[0]
        // Убираем имя команды из массива аргументов
        args = args.copyOfRange(1, args.size)

        // Проверяем существование команды
        if (!commands.containsKey(name)) {
            println("Command '$name' doesn't exist.")
            commands["help"]?.execute()
            return
        }

        val command = commands[name]!!
        val commandDefinition = command.javaClass.getAnnotation(CommandDefinition::class.java)

        // Валидируем количество аргументов
        if (commandDefinition.argsNumber != args.size) {
            System.err.println("Invalid call! Right signature is '${commandDefinition.arguments}'")
            return
        }

        // Можно во время практики усложнить и добавить аннотацию,
        // которая будет отвечать за метод выполнения:
        // например, динамически валидировать и рассчитывать аргументы
        command.execute(*args)
        println("Please, input the new command:")
    }

    /**
     * Встроенная команда help для вывода списка доступных команд.
     *
     * Демонстрирует:
     * - Создание команды без DI (создаётся напрямую в конструкторе)
     * - Использование Reflection API для чтения аннотаций
     * - Итерацию по зарегистрированным командам
     *
     * Примечание: Класс не может быть private из-за ограничений модульной системы Java 9+
     * при работе с рефлексией. Используется internal для ограничения видимости в пределах модуля.
     */
    @CommandDefinition(
        name = "help",
        description = "List all possible commands"
    )
    internal class HelpCommand(private val commands: Map<String, Command>) : Command {
        /**
         * Выводит список всех зарегистрированных команд с их описаниями.
         * Использует Reflection API для получения метаданных из аннотации @CommandDefinition.
         */
        override fun execute(vararg args: String) {
            println("Available commands:")
            commands.forEach { (key, command) ->
                val definition = command.javaClass.getAnnotation(CommandDefinition::class.java)
                println(" | $key ${definition.arguments} - ${definition.description}")
            }
        }
    }
}
