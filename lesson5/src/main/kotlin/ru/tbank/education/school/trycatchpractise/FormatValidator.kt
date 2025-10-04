package ru.tbank.education.school.trycatchpractise

/**
 * Кастомное исключение, выбрасываемое при неверном формате файла.
 */
class InvalidFileFormatException(message: String) : Exception(message)

/**
 * Интерфейс для чтения файла с проверкой формата.
 */
interface FormatValidator {

    /**
     * Читает файл и проверяет его формат.
     *
     * Формат: каждая строка должна содержать имя и возраст, разделённые запятой.
     * Например: "Иван,25"
     *
     * @param path путь к файлу
     * @return список строк, если формат корректен
     * @throws InvalidFileFormatException если формат неверен
     */
    fun validateAndRead(path: String): List<String>
}