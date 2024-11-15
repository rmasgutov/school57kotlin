package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

/**
 * Класс для анализа содержимого файла.
 */
object Analyzer {

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
            val words = lines.flatMap { it.split(" ").map { word -> word.lowercase() } }

            val uniqueWords = words.toSet()
            val averageWordLength = if (words.isNotEmpty()) words.sumOf { it.length } / words.size else 0

            output.writeText("Общее количество строк: ${lines.size}\n")
            output.writeText("Общее количество слов: ${words.size}\n")
            output.writeText("Уникальные слова: ${uniqueWords.size}\n")
            output.writeText("Средняя длина слов: ${averageWordLength}\n")
        } catch (e: FileNotFoundException) {
            println("Файл не обнаружен: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        } catch (e: NumberFormatException) {
            println("Не число: ${e.message}")
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
            val words = lines.flatMap { it.split(" ").map { word -> word.lowercase() } }

            val uniqueWords = words.toSet()
            val averageWordLength = if (words.isNotEmpty()) words.sumOf { it.length } / words.size else 0

            Files.write(output, "Общее количество строк: ${lines.size}\n".toByteArray())
            Files.write(output, "Общее количество слов: ${words.size}\n".toByteArray(), StandardOpenOption.APPEND)
            Files.write(output, "Уникальные слова: ${uniqueWords.size}\n".toByteArray(), StandardOpenOption.APPEND)
            Files.write(output, "Средняя длина слов: ${averageWordLength}\n".toByteArray(), StandardOpenOption.APPEND)
        } catch (e: FileNotFoundException) {
            println("Файл не обнаружен: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        } catch (e: NumberFormatException) {
            println("Не число: ${e.message}")
        } catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }

}
