package ru.tbank.education.school.homework

import java.io.BufferedWriter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException
import java.nio.file.AccessDeniedException
import java.nio.file.Files
import java.nio.file.InvalidPathException
import java.nio.file.NoSuchFileException
import java.nio.file.Paths
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
            val inputFile = File(inputFilePath)
            val lines = inputFile.readLines()
            val wordCount = lines.sumOf { it.split("\\s+".toRegex()).size }
            BufferedWriter(FileWriter(outputFilePath, true)).use { writer ->
                for (line in listOf("Общее количество строк: ${lines.size}", "Общее количество слов: $wordCount")) {
                    writer.write(line)
                    writer.newLine()
                }
            }
            true
        } catch (exception: FileNotFoundException) {
            println("Файл не найден: ${exception.message}")
            false
        } catch (exception: SecurityException) {
            println("Нет доступа к файлу: ${exception.message}")
            false
        } catch (exception: IOException) {
            println("Ошибка ввода/вывода: ${exception.message}")
            false
        } catch (exception: Exception) {
            println("Непредвиденная ошибка: ${exception.message}")
            false
        }
    }
}

class NIOFileAnalyzer : FileAnalyzer {
    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        return try {
            val inputFileNIOPath = Paths.get(inputFilePath)
            val outputFileNIOPath = Paths.get(outputFilePath)
            val lines = Files.readAllLines(inputFileNIOPath)
            val wordCount = lines.sumOf { it.split("\\s+".toRegex()).size }
            Files.write(
                outputFileNIOPath,
                listOf(
                    "Общее количество строк: ${lines.size}",
                    "Общее количество слов: $wordCount"
                ),
                StandardOpenOption.APPEND
            )
            true
        } catch (exception: NoSuchFileException) {
            println("Файл не найден: ${exception.message}")
            false
        } catch (exception: AccessDeniedException) {
            println("Нет доступа к файлу: ${exception.message}")
            false
        } catch (exception: InvalidPathException) {
            println("Некорректный путь: ${exception.message}")
            false
        } catch (exception: Exception) {
            println("Непредвиденная ошибка: ${exception.message}")
            false
        }
    }
}