package ru.tbank.education.school.lesson10.dependency.injection.services.commands

/**
 * Аннотация для определения метаданных команды.
 *
 * Используется для:
 * - Автоматической регистрации команд в CommandHandler
 * - Определения имени команды и её псевдонимов
 * - Валидации количества аргументов
 * - Генерации справки (help)
 *
 * @property name основное имя команды
 * @property description описание функциональности команды
 * @property arguments строка-описание аргументов (для справки)
 * @property aliases массив альтернативных имён команды
 * @property argsNumber ожидаемое количество аргументов для валидации
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class CommandDefinition(
    val name: String,
    val description: String,
    val arguments: String = "",
    val aliases: Array<String> = [],
    val argsNumber: Int = 0
)
