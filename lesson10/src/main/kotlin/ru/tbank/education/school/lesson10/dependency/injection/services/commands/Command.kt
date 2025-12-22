package ru.tbank.education.school.lesson10.dependency.injection.services.commands

/**
 * Интерфейс команды для реализации паттерна Command.
 *
 * Все команды приложения должны реализовывать этот интерфейс.
 * Паттерн Command инкапсулирует запрос как объект, позволяя
 * параметризовать клиентов с различными запросами.
 */
interface Command {
    /**
     * Выполняет команду с переданными аргументами.
     *
     * @param args аргументы команды (vararg позволяет передавать переменное количество аргументов)
     */
    fun execute(vararg args: String)
}
