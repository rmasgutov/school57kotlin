package ru.tbank.education.school.homework

import java.io.File
import java.io.FileWriter
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Интерфейс для подсчёта строк и слов в файле.
 */
interface FileAnalyzer {

    /**
     * Считает количество строк и слов в указанном входном файле и записывает результат в выходной файл.
     *
     * Словом считается последовательность символов, разделённая пробелами,
     * табуляциями или знаками перевода строки. Пустые части после разделения не считаются словами.
     *
     * @param inputFilePath путь к входному текстовому файлу
     * @param outputFilePath путь к выходному файлу, в который будет записан результат
     * @return true если операция успешна, иначе false
     */
    fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean
}

class IOFileAnalyzer : FileAnalyzer {
    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        return try {
            val inputFile = File(inputFilePath)
            val outputFile = File(outputFilePath)

            val lines = inputFile.readLines()
            val wordCount = lines.sumOf { line ->
                line.split(Regex("\\s+"))
                    .count { it.isNotBlank() }
            }

            FileWriter(outputFile).use { writer ->
                writer.write("Количество слов: $wordCount\nКоличество строк: ${lines.size}")
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    class NIOFileAnalyzer : FileAnalyzer {
        override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
            return try {
                val lines = Files.readAllLines(Paths.get(inputFilePath), StandardCharsets.UTF_8)
                val wordCount = lines.sumOf { line ->
                    line.split(Regex("\\s+"))
                        .count { it.isNotBlank() }
                }

                Files.write(
                    Paths.get(outputFilePath),
                    "Количество слов: $wordCount\nКоличество строк: ${lines.size}".toByteArray(StandardCharsets.UTF_8)
                )
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}