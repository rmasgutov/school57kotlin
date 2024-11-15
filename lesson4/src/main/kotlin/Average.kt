package ru.tbank.education.school

import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Класс для вычисления среднего арифметического.
 */
object Average {
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
        var result = mutableListOf<Double>()
        try {
            val lines = File(source).readLines()
            for (line in lines) {
                val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
                val average = numbers.average()
                result.add(average)
            }
        } catch (e: Exception) {
            println("Ошибка при чтении файла: ${e.message}")
        }
        try {
            File(target).printWriter().use { writer ->
                for (line in result) {
                    writer.println(line) // Записываем каждую строку в файл
                }
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
        var result = mutableListOf<Double>()
        try {
            Files.newBufferedReader(source).use { reader ->
                reader.lineSequence().forEach { line ->
                    val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
                    val average = numbers.average()
                    result.add(average)
                }
            }
        } catch (e: Exception) {
            println("Ошибка при чтении файла: ${e.message}")
        }
        try {
            PrintWriter(Files.newBufferedWriter(target)).use { writer ->
                for (line in result) {
                    writer.println(line) // Записываем каждую строку в файл
                }
            }
        } catch (e: IOException) { println("Ошибка при записи в файл: ${e.message}") }
    }
}
