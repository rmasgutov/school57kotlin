package ru.tbank.education.school


import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
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
            val inputFileIO = File(source)
            val outputFileIO = File(target)
            val lines = inputFileIO.readLines()
            var words = 0
            var totalLenth = 0.0
            var cntOfLines = 0
            val uniqueWords = mutableSetOf<String>()
            for (line in lines) {
                words += line.split(" ").size
                cntOfLines++
                uniqueWords.addAll(line.split(" "))
                for (word in line.split(" ")) totalLenth += word.length
            }
            val result = """
                Общее количество строк: $cntOfLines
                Общее количество слов: $words
                Уникальные слова: ${uniqueWords.size}
                Средняя длина слов: ${totalLenth / words}
            """.trimIndent()
            outputFileIO.writeText(result)
        } catch (e: FileNotFoundException) {
            println("Такого файла не существует:\n${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлом(и):\n${e.message}")
        }
        catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
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
            val inputFileNIO = Paths.get(source)
            val outputFileNIO = Paths.get(target)
            val lines = Files.readAllLines(inputFileNIO)
            var words = 0
            var totalLenth = 0.0
            var cntOfLines = 0
            val uniqueWords = mutableSetOf<String>()
            for (line in lines) {
                words += line.split(" ").size
                cntOfLines++
                uniqueWords.addAll(line.split(" "))
                for (word in line.split(" ")) totalLenth += word.length
            }
            val result = """
                Общее количество строк: $cntOfLines
                Общее количество слов: $words
                Уникальные слова: ${uniqueWords.size}
                Средняя длина слов: ${totalLenth / words}
            """.trimIndent()
            Files.write(outputFileNIO, result.toByteArray())
        } catch (e: FileNotFoundException) {
            println("Такого файла не существует:\n${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлом(и):\n${e.message}")
        }
        catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }
}
