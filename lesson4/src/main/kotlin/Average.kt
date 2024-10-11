package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Класс для вычисления среднего арифметического.
 */
object Average {

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java IO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileIO(source: String, target: String) {
        try {
            val input = File(source)
            val output = File(target)

            val lines = input.readLines()
            val mappedLines = lines.map { line ->
                val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
                if (numbers.isNotEmpty()) numbers.sum() / numbers.size else 0
            }

            output.writeText(mappedLines.joinToString("\n"))
        } catch (e: FileNotFoundException) {
            println("Файл не обнаружен: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        } catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java NIO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileNIO(source: String, target: String) {
        try {
            val input = Paths.get(source)
            val output = Paths.get(target)

            val lines = Files.readAllLines(input)
            val mappedLines = lines.map { line ->
                val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
                if (numbers.isNotEmpty()) numbers.sum() / numbers.size else 0
            }

            Files.write(output, mappedLines.joinToString("\n").toByteArray())
        } catch (e: FileNotFoundException) {
            println("Файл не обнаружен: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        } catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }

}
