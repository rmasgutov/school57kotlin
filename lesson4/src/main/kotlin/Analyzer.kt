package ru.tbank.education.school

import java.io.*
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import kotlin.RuntimeException
import kotlin.io.path.exists
import kotlin.io.path.readLines
import kotlin.io.path.writeBytes

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
        val InputFile = File(source)
        val OutputFile = File(target)
        if (!InputFile.exists()) {
            throw FileNotFoundException("Входной файл не найден: $source")
        }
        if (!OutputFile.exists()) {
            throw FileNotFoundException("Выходной файл не найден: $source")
        }
        var numberOfLines = 0
        var numberOfWords = 0
        var count = mutableSetOf<String>()
        var lengthOfText = 0
        try {
            BufferedReader(FileReader(source)).use { reader ->
                val lines = reader.readLines()
                numberOfLines = lines.size
                for (line in lines) {
                    val words = line.split(" ").mapNotNull { it.toString() }
                    numberOfWords += words.size
                    for (word in words) {
                        count.add(word.lowercase())
                        lengthOfText += word.length
                    }
                }
            }
        } catch (e: IOException) {
            println("Ошибка при чтении файла: ${e.message}")
        }
        var averageLength = 0
        if (numberOfWords != 0) averageLength = lengthOfText / numberOfWords
        try {
            File(target).printWriter().use { writer ->
                writer.write("Общее количество строк: $numberOfLines \n")
                writer.write("Общее количество слов: $numberOfWords \n")
                writer.write("Уникальные слова: ${count.size} \n")
                writer.write("Средняя длина слов: $averageLength \n")
            }
        } catch (e: IOException) { println("Ошибка при записи в файл: ${e.message}") }
    }
    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java NIO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileNIO(name: String, to: String) {
        val source = Paths.get(name)
        val target = Paths.get(to)
        if (!Files.exists(source)) {
            throw FileNotFoundException("Входной файл не найден: $source")
        }
        if (!Files.exists(target)) {
            throw FileNotFoundException("Выходной файл не найден: $target")
        }
        try {
            var numberOfLines = 0
            var numberOfWords = 0
            var count = mutableSetOf<String>()
            var lengthOfText = 0
            var line: String
            BufferedReader(InputStreamReader(Files.newInputStream(source))).use { reader ->
                val data = mutableListOf<List<String>>()
                val lines = Files.readAllLines(Paths.get(name))
                for (line in lines) {
                    numberOfLines++
                    val words = line.split(" ").mapNotNull { it.toString() }
                    numberOfWords += words.size
                    for (word in words) {
                        count.add(word.lowercase())
                        lengthOfText += word.length
                    }
                }
            }
            var averageLength = 0
            if (numberOfWords != 0) averageLength = lengthOfText / numberOfWords
            BufferedWriter(OutputStreamWriter(Files.newOutputStream(target))).use { writer ->
                writer.write("Общее количество строк: $numberOfLines")
                writer.newLine()
                writer.write("Общее количество слов: $numberOfWords")
                writer.newLine()
                writer.write("Уникальные слова: ${count.size}")
                writer.newLine()
                writer.write("Средняя длина слов: $averageLength")
            }
        } catch (e: Exception) {
            println("Ошибка при чтении файла: ${e.message}")
        }
    }
}
