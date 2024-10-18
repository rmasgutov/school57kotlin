package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

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
            val inputFile = File(source)
            val outputFile = File(target)

            if (!inputFile.exists()) {
                throw FileNotFoundException("Input file not found: $source")
            }

            val lines = mutableListOf<String>()
            var wordCount = 0
            val uniqueWords = mutableSetOf<String>()

            inputFile.forEachLine { line ->
                lines.add(line)
                val words = line.split("\\s+".toRegex()).filter { it.isNotEmpty() }
                wordCount += words.size
                uniqueWords.addAll(words.map { it.lowercase() })
            }

            val averageWordLength = if (wordCount > 0) {
                lines.joinToString(" ").split("\\s+".toRegex()).map { it.length }.average()
            } else {
                0.0
            }

            outputFile.printWriter().use { out ->
                out.println("Общее количество строк: ${lines.size}")
                out.println("Общее количество слов: $wordCount")
                out.println("Уникальные слова: ${uniqueWords.size}")
                out.printf("Средняя длина слов: %.2f%n", averageWordLength)
            }
        } catch (e: FileNotFoundException) {
            println("Error: ${e.message}")
        } catch (e: IOException) {
            println("IO Error: ${e.message}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
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
            val inputPath: Path = Paths.get(source)
            val outputPath: Path = Paths.get(target)

            if (!Files.exists(inputPath)) {
                throw FileNotFoundException("Input file not found: $source")
            }

            val lines = mutableListOf<String>()
            var wordCount = 0
            val uniqueWords = mutableSetOf<String>()

            Files.lines(inputPath).use { lineStream ->
                lineStream.forEach { line ->
                    lines.add(line)
                    val words = line.split("\\s+".toRegex()).filter { it.isNotEmpty() }
                    wordCount += words.size
                    uniqueWords.addAll(words.map { it.lowercase() })
                }
            }

            val averageWordLength = if (wordCount > 0) {
                lines.joinToString(" ").split("\\s+".toRegex()).map { it.length }.average()
            } else {
                0.0
            }

            Files.write(outputPath, listOf(
                "Общее количество строк: ${lines.size}",
                "Общее количество слов: $wordCount",
                "Уникальные слова: ${uniqueWords.size}",
                "Средняя длина слов: %.2f".format(averageWordLength)
            ))
        } catch (e: FileNotFoundException) {
            println("Error: ${e.message}")
        } catch (e: IOException) {
            println("IO Error: ${e.message}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}