package ru.tbank.education.school.homework

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.InvalidPathException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.NoSuchFileException

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
    private fun countWords(line: String): Int {
        return if (line.isBlank()) {
            0
        }
        else {
            line.trim().split("\\s+".toRegex()).count()
        }
    }

    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        return try {
            val inputFile = File(inputFilePath)
            val lines = inputFile.readLines()
            val lineCount = lines.size
            val wordCount = lines.sumOf { countWords(it) }

            File(outputFilePath).writeText(
                "Общее количество строк: $lineCount\n" +
                        "Общее количество слов: $wordCount"
            )
            true
        }
        catch (e:FileNotFoundException)  {
            println("Файл не найден: ${e.message}")
            false
        }
        catch (e: IOException) {
            println("Ошибка ввода-вывода: ${e.message}")
            false
        }
        catch (e : SecurityException){
            println("Ошибка безопасности: ${e.message}")
            false
        }
        catch (e: InvalidPathException){
            println("Неверный путь файла: ${e.message}")
            false
        }
    }
}

class NIOFileAnalyzer : FileAnalyzer {
    private fun countWords(line: String): Int {
        return if (line.isBlank()) {
            0
        }
        else {
            line.trim().split("\\s+".toRegex()).count()
        }
    }

    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        return try {
            val inputPath = Paths.get(inputFilePath)
            val outputPath = Paths.get(outputFilePath)
            val lines = Files.readAllLines(inputPath)
            val lineCount = lines.size
            val wordCount = lines.sumOf { countWords(it) }
            val res = "Общее количество строк: $lineCount\n" +
                    "Общее количество слов: $wordCount"
            Files.write(outputPath, res.toByteArray())
            true
        }
        catch (e:NoSuchFileException) {
            println("Файл не найден: ${e.message}")
            false
        }
        catch (e: SecurityException){
            println("Ошибка безопасности: ${e.message}")
            false
        }
        catch (e:IOException) {
            println("Ошибка ввода-вывода: ${e.message}")
            false
        }
        catch (e:InvalidPathException) {
            println("Некорректный путь: ${e.message}")
            false
        }
    }
}