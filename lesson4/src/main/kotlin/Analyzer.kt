package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object Analyzer {
    fun processFileIO(source: String, target: String) {
        try {
            val fin = File(source)
            val fout = File(target)
            val lines = fin.readLines()
            val words = lines.flatMap { it.split(" ").map { word -> word.lowercase() } }
            val meanWordsLen: Int
            if (words.isNotEmpty()) {
                meanWordsLen = words.sumOf { it.length } / words.size
            } else {
                meanWordsLen = 0
            }
            fout.writeText(
                "Общее количество строк: ${lines.size}\nОбщее количество слов: ${words.size}\n" +
                    "Уникальные слова: ${words.toSet().size}\nСредняя длина слов: ${meanWordsLen}\n"
            )
        } catch (e: FileNotFoundException) {
            println("Такого файла не существует: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        }
    }
    fun processFileNIO(source: String, target: String) {
        try {
            val fin = Paths.get(source)
            val fout = Paths.get(target)
            val lines = Files.readAllLines(fin)
            val words = lines.flatMap { it.split(" ").map { word -> word.lowercase() } }
            val meanWordsLen: Int
            if (words.isNotEmpty()) {
                meanWordsLen = words.sumOf { it.length } / words.size
            } else {
                meanWordsLen = 0
            }
            Files.write(
                fout,
                "Общее количество строк: ${lines.size}\nОбщее количество слов: ${words.size}\nУникальные слова: ${words.toSet().size}\nСредняя длина слов: ${meanWordsLen}\n".toByteArray()
            )
        } catch (e: FileNotFoundException) {
            println("Такого файла не существует: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        }
    }
}
