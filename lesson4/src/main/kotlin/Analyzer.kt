package ru.tbank.education.school

/**
 * Класс для анализа содержимого файла.
 */

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.io.IOException

object Analyzer {
    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java IO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileIO(source: String, target: String) {
        try {
            val inputFile = File(source)
            val outputFile = File(target)

            if (!inputFile.exists()) {
                println("Input file does not exist: $source")
                return
            }

            val lines = inputFile.readLines()
            val totalLines = lines.size
            val words = lines.flatMap { it.split("\\s+".toRegex()) }
            val totalWords = words.size
            val uniqueWords = words.map { it.lowercase() }.toSet().size
            val averageWordLength = if (totalWords > 0) words.sumOf { it.length }.toDouble() / totalWords else 0.0

            outputFile.writeText("""
                Общее количество строк: $totalLines
                Общее количество слов: $totalWords
                Уникальные слова: $uniqueWords
                Средняя длина слов: %.2f
            """.trimIndent().format(averageWordLength))

        } catch (e: IOException) {
            println("An error occurred: ${e.message}")
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
            val inputPath = Paths.get(source)
            val outputPath = Paths.get(target)

            if (!Files.exists(inputPath)) {
                println("Input file does not exist: $source")
                return
            }

            val lines = Files.readAllLines(inputPath)
            val totalLines = lines.size
            val words = lines.flatMap { it.split("\\s+".toRegex()) }
            val totalWords = words.size
            val uniqueWords = words.map { it.lowercase() }.toSet().size
            val averageWordLength = if (totalWords > 0) words.sumOf { it.length }.toDouble() / totalWords else 0.0

            val result = """
                Общее количество строк: $totalLines
                Общее количество слов: $totalWords
                Уникальные слова: $uniqueWords
                Средняя длина слов: %.2f
            """.trimIndent().format(averageWordLength)

            Files.write(outputPath, result.toByteArray())

        } catch (e: IOException) {
            println("An error occurred: ${e.message}")
        }
    }
}
