package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object Analyzer {
    fun processFileIO(source: String, target: String) {
        try {
            val inputFileIO = File(source)
            val outputFileIO = File(target)
            val lines = inputFileIO.readLines()
            val words = lines.flatMap { it.split(" ").map { word -> word.lowercase()}}
            val meanWordsLen: Int = if (words.isNotEmpty()) words.sumOf { it.length } / words.size
            else 0
            outputFileIO.writeText("Общее количество строк: ${lines.size}\nОбщее количество слов: ${words.size}\n" +
                    "Уникальные слова: ${words.toSet().size}\nСредняя длина слов: ${meanWordsLen}\n")
        }
        catch (e: FileNotFoundException) {
            println("Такого файла не существует: ${e.message}")
        }
        catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        }
        catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }
    fun processFileNIO(source: String, target: String) {
        try {
            val inputFileNIO = Paths.get(source)
            val outputFileNIO = Paths.get(target)
            val lines = Files.readAllLines(inputFileNIO)
            val words = lines.flatMap { it.split(" ").map { word -> word.lowercase()}}
            val meanWordsLen: Int = if (words.isNotEmpty()) words.sumOf { it.length } / words.size
            else 0
            Files.write(outputFileNIO, "Общее количество строк: ${lines.size}\nОбщее количество слов: ${words.size}\nУникальные слова: ${words.toSet().size}\nСредняя длина слов: ${meanWordsLen}\n".toByteArray())
        }
        catch (e: FileNotFoundException) {
            println("Такого файла не существует: ${e.message}")
        }
        catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        }
        catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }
}
