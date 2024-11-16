package ru.tbank.education.school

import java.io.File
import java.io.IOException
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.FileAlreadyExistsException

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
            val inputFileIO = File(source)
            val outputFileIO = File(target)

            val lines = inputFileIO.readLines()
            val words = inputFileIO.readText().split("\\s+".toRegex())
            val uniqueWords = words.toSet()
            val averageWordsLen = if (words.isNotEmpty()) words.map { it.length }.average() else 0

            outputFileIO.writeText("Общее количество строк: ${lines.size}\n")
            outputFileIO.writeText("Общее количество слов: ${words.size}\n")
            outputFileIO.writeText("Уникальные слова: ${uniqueWords.size}\n")
            outputFileIO.writeText("Средняя длина слов: ${averageWordsLen}\n")
        }
        catch (e: FileNotFoundException) {
            println("Файл не найден: ${e.message}")
        }
        catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        }
        catch (e: Exception) {
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
            val inputFileNIO = Paths.get(source)
            val outputFileNIO = Paths.get(target)

            val lines = Files.readAllLines(inputFileNIO)
            val words = Files.readString(inputFileNIO).split("\\s+".toRegex())
            val uniqueWords = words.toSet()
            val averageWordsLen = if (words.isNotEmpty()) words.map { it.length }.average() else 0

            Files.write(outputFileNIO, "Общее количество строк: ${lines.size}\n".toByteArray())
            Files.write(outputFileNIO, "Общее количество слов: ${words.size}\n".toByteArray())
            Files.write(outputFileNIO, "Уникальные слова: ${uniqueWords.size}\n".toByteArray())
            Files.write(outputFileNIO, "Средняя длина слов: ${averageWordsLen}\n".toByteArray())
        }
        catch (e: FileAlreadyExistsException) {
            println("Файл уже существует: ${e.message}")
        }
        catch (e: java.nio.file.NoSuchFileException) {
            println("Файл не найден: ${e.message}")
        }
        catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        }
        catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }
}
