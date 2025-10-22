package ru.tbank.education.school.files

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

/**
 * Задание:
 *
 * Класс ConfigService должен:
 * 1. Читать все строки конфигурационного файла по указанному пути.
 * 2. Гарантированно закрывать файл после чтения, даже если произошло исключение.
 * 3. При ошибке чтения выбрасывать IOException наружу.
 */
class ConfigService(private val path: String) {

    fun readConfig(): List<String> {
        val file = File(path)
        val lines = mutableListOf<String>()

        try {
            BufferedReader(FileReader(file)).use { reader ->
                var line = reader.readLine()
                while (line != null) {
                    lines.add(line)
                    line = reader.readLine()
                }
            }
        } catch (e: IOException) {
            throw IOException("Ошибка при чтении файла: ${e.message}", e)
        }

        return lines
    }
}