package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
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
        try {
            val inputFile = File(source)
            val outputFile = File(target)

            if (!inputFile.exists()) {
                throw FileNotFoundException("Input file not found: $source")
            }

            val results = mutableListOf<Double>()

            inputFile.forEachLine { line ->
                val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
                val average = if (numbers.isNotEmpty()) numbers.average() else 0.0
                results.add(average)
            }

            outputFile.printWriter().use { out ->
                results.forEach { out.println(it) }
            }
        } catch (e: FileNotFoundException) {
            println("Error: ${e.message}")
        } catch (e: IOException) {
            println("IO Error: ${e.message}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
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
            val inputPath: Path = Paths.get(source)
            val outputPath: Path = Paths.get(target)

            if (!Files.exists(inputPath)) {
                throw FileNotFoundException("Input file not found: $source")
            }

            val results = mutableListOf<Double>()

            Files.lines(inputPath).use { lines ->
                lines.forEach { line ->
                    val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
                    val average = if (numbers.isNotEmpty()) numbers.average() else 0.0
                    results.add(average)
                }
            }

            Files.write(outputPath, results.map { it.toString() })
        } catch (e: FileNotFoundException) {
            println("Error: ${e.message}")
        } catch (e: IOException) {
            println("IO Error: ${e.message}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}