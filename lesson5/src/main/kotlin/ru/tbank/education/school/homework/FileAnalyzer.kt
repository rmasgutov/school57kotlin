package ru.tbank.education.school.homework

import java.io.*
import java.nio.file.*
import java.io.IOException

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
        var lineCount = 0
        var wordCount = 0
        try{
            val file = File(inputFilePath)
            BufferedReader(FileReader(file)).use {reader ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    lineCount++
                    val words = line!!.trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }
                    wordCount += words.size
                }
            }
            val outputFile = File(outputFilePath)
            BufferedWriter(FileWriter(outputFile)).use { writer ->
                writer.write("Общее кол-во строк: $lineCount\n.")
                writer.write("Общее кол-во слов: $wordCount\n.")
            }
            return true
        } catch (e: Exception) {
            println("Ошибка при попытке анализа файла: ${e.message}. ")
            return false
        }
    }
}

class NIOFileAnalyzer : FileAnalyzer {
    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        var lineCount = 0
        var wordCount = 0
        try {
            val inputPath = Paths.get(inputFilePath)
            val lines = Files.readAllLines(inputPath)
            lineCount = lines.size
            for (line in lines) {
                val words = line.trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }
                wordCount += words.size
            }
            val outputPath = Paths.get(outputFilePath)
            val outputContent = "Общее кол-во строк: $lineCount\n." +
                    "Общее кол-во слов: $wordCount\n."
            Files.write(outputPath, outputContent.toByteArray())
            return true
        } catch (e: IOException) {
            println("Ошибка при попытке анализа файла: ${e.message}.")
            return false
        } catch (e: Exception) {
            println("Произошла ошибка: ${e.message}.")
            return false
        }
    }
}