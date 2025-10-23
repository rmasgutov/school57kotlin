package ru.tbank.education.school.homework

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption


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
            val inputFile = java.io.File(inputFilePath)
            val outputFile = java.io.File(outputFilePath)
            var countStr = 0
            var countWords = 0
            inputFile.bufferedReader().use { reader ->
                reader.forEachLine { line ->
                    countStr++
                    val words = line.trim().split("\\s+".toRegex())
                    countWords += words.size
                }
            }
            val res = "Общее количество строк: $countStr\nОбщее количество слов: $countWords"

            outputFile.bufferedWriter().use { writer ->
                writer.write(res)
            }
            true
        } catch (e: IOException) {
            return false
        } catch (ee: Exception) {
            return false
        }
    }
}

class NIOFileAnalyzer : FileAnalyzer {
    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        return try {
            val text = Files.readAllLines(Path.of(inputFilePath))
            var counter_str = 0
            var counter_word = 0
            for (i in text.indices) {
                counter_str++
                for (word in text[i].split("\\s+".toRegex())) {
                    counter_word++
                }
            }
            val res = "Общее количество строк: $counter_str\nОбщее количество слов: $counter_word"
            Files.writeString(
                Path.of(outputFilePath),
                res,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            )
            true
        } catch (e: IOException) {
            return false
        } catch (ee: Exception) {
            return false
        }
    }
}