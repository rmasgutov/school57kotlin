package org.example

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import kotlin.collections.mutableMapOf


object Analyzer {

    fun processFileIO(source: String, target: String) {
        try {
            val data = File(source)

            val ans = File(target)

            val lines = data.readLines()

            val stats = countStats(lines)

            ans.writeText("Общее количество строк: ${stats["rows"]}\n")
            ans.appendText("Общее количество слов: ${stats["countWords"]}\n")
            ans.appendText("Уникальных слов: ${stats["uniqueWords"]}\n")
            ans.appendText("Средняя длина слов: ${stats["averageLength"]}\n")

        } catch (e: FileNotFoundException) {
            println("Файл не найден: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка во время работы с файлом: ${e.message}")
        } catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }


    fun processFileNIO(source: String, target: String) {

        try {
            val data = Paths.get(source)

            val ans = Paths.get(target)

            val lines = Files.readAllLines(data)

            val stats = countStats(lines)

            Files.write(ans, "Общее количество строк: ${stats["rows"]}\n".toByteArray())
            Files.write(ans, "Общее количество слов: ${stats["countWords"]}\n".toByteArray(), StandardOpenOption.APPEND)
            Files.write(ans, "Уникальных слов: ${stats["uniqueWords"]}\n".toByteArray(), StandardOpenOption.APPEND)
            Files.write(ans, "Средняя длина слов: ${stats["averageLength"]}\n".toByteArray(), StandardOpenOption.APPEND)


        } catch (e: NoSuchFileException) {
            println("Файл не найден: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка во время работы с файлом: ${e.message}")
        } catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }
    private fun countStats(lines: List<String>): MutableMap <String, Int> {

        val statistics = mutableMapOf <String, Int>()
        val words = lines.flatMap {
            it.split("\\s+".toRegex())
                .map { word -> word.lowercase() }
                .filter { word -> word.isNotEmpty() } // Фильтруем только непустые слова
        }

        println(words)
        statistics["rows"] = lines.size
        statistics["countWords"] = words.size
        statistics["uniqueWords"] = words.toSet().size
        statistics["averageLength"] = if (words.isNotEmpty()) words.sumOf { it.length } / words.size else 0
        println(statistics)
        return statistics
    }

}