package ru.tbank.education.school.files

/**
 * Задание:
 *
 * Класс ConfigService должен:
 * 1. Чтение всех строк конфигурационного файла по указанному пути.
 * 2. Гарантированно закрывать файл после чтения, даже если произошло исключение.
 * 3. При ошибке чтения выбрасывать IOException наружу.
 *
 * В текущей реализации есть баг: BufferedReader не закрывается при IOException.
 * Ваша задача исправить класс так, чтобы ресурс закрывался корректно.
 */
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class ConfigService(private val path: String) {

    fun readConfig(): List<String> {
        val file = File(path)
        val reader = BufferedReader(FileReader(file))
        val lines = mutableListOf<String>()
        try {
            var line = reader.readLine()
            while (line != null) {
                lines.add(line)
                line = reader.readLine()
            }
        } catch (e: IOException) {
            println("Ошибка при чтении файла: ${e.message}")
        }
        return lines
    }
}
