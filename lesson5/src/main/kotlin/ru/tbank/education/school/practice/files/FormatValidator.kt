package ru.tbank.education.school.practice.files

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

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

fun validateLine(line: String, lineNumber: Int) {
    val parts = line.split(",".toRegex()).map { it.trim() }
    if (parts.size != 2) {
        throw InvalidFileFormatException("[Строка $lineNumber] Указаны менее или более двух значений на строке")
    }

    val name = parts[0]
    val ageString = parts[1]

    if (name.isEmpty()) {
        throw InvalidFileFormatException("[Строка $lineNumber] Имя не может быть пустым")
    }

    if (ageString.isEmpty() || !ageString.all { it.isDigit() }) {
        throw InvalidFileFormatException("[Строка $lineNumber] Возраст должен быть целым числом")
    }

    val age = ageString.toInt()
    if (age < 0) {
        throw InvalidFileFormatException("[Строка $lineNumber] Возраст не может быть отрицательным")
    }
}

class IOFormatValidator : FormatValidator {
    override fun validateAndRead(path: String): List<String> {
        val file = File(path)
        val result = mutableListOf<String>()

        try {
            file.bufferedReader().use { reader ->
                var lineNumber = 1
                var line: String?

                while (true) {
                    line = reader.readLine()
                    if (line == null) break

                    if (line.trim().isEmpty()) {
                        lineNumber++
                        continue
                    }

                    validateLine(line, lineNumber)

                    result.add(line)
                    lineNumber++
                }
            }
            return result
        } catch (ex: IOException) {
            println("Ошибка ввода/вывода: ${ex.message}")
            return emptyList()
        } catch (ex: InvalidFileFormatException) {
            println("Неверный формат файла: ${ex.message}")
            return emptyList()
        }
    }
}

class NIOFormatValidator : FormatValidator {
    override fun validateAndRead(path: String): List<String> {
        val filePath = Paths.get(path)
        val result = mutableListOf<String>()

        try {
            val lines = Files.readAllLines(filePath)
            for (i in 0..lines.size - 1) {
                val line = lines[i]

                if (line.trim().isEmpty()) continue

                val lineNumber = i + 1

                validateLine(line, lineNumber)

                result.add(line)
            }
            return result
        } catch (ex: IOException) {
            println("Ошибка ввода/вывода: ${ex.message}")
            return emptyList()
        } catch (ex: InvalidFileFormatException) {
            println("Неверный формат файла: ${ex.message}")
            return emptyList()
        }
    }
}