package ru.tbank.education.school.trycatchpractise

import java.io.File
import java.io.IOException

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


class FormatValidatorImpl() : FormatValidator {
    override fun validateAndRead(path: String): List<String> {
        val file = File(path)
        try {
            for (fileLine in file.readLines()) {
                if (fileLine.length <= 3) throw InvalidFileFormatException("Invalid file format")
                if (!fileLine.contains(",")) throw InvalidFileFormatException("Invalid file format")
            }
        }
        catch (e: InvalidFileFormatException) {}
        return file.readLines()
    }
}