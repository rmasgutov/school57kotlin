package ru.tbank.education.school.trycatchpractise

/**
 * Интерфейс безопасного парсера чисел.
 */
interface SafeNumberParser {

    /**
     * Преобразует строку в число.
     *
     * Если строка не является корректным числом, метод должен
     * поймать исключение и вернуть `null`.
     *
     * @param input строка для конвертации
     * @return число или null, если строка некорректна
     */
    fun parseInt(input: String): Int?
}


class NumberParser() : SafeNumberParser {
    override fun parseInt(input: String): Int? {
        try {
            return input.toInt()
        }
        catch (e: Exception) {
            return null
        }
    }
}