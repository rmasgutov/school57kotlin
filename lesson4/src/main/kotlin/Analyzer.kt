package ru.tbank.education.school

import java.io.*
import java.nio.file.*
import java.nio.file.StandardOpenOption

object Analyzer {
    fun analyzeIO(inputPath: String, outputPath: String) {
        try {
            BufferedReader(FileReader(inputPath)).use { reader ->
                val lines = mutableListOf<String>()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    lines.add(line!!)
                }

                val analysis = analyzeText(lines)

                BufferedWriter(FileWriter(outputPath)).use { writer ->
                    for (result in analysis) {
                        writer.write(result)
                        writer.newLine()
                    }
                }
            }
        } catch (e: IOException) {
            println("Ошибка при работе с файлами (IO): ${e.message}")
        }
    }

    fun analyzeNIO(inputPath: String, outputPath: String) {
        val input = Paths.get(inputPath)
        val output = Paths.get(outputPath)

        try {
            val lines = Files.readAllLines(input)
            val analysis = analyzeText(lines)

            Files.write(output, analysis, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
        } catch (e: IOException) {
            println("Ошибка при работе с файлами (NIO): ${e.message}")
        }
    }

    private fun analyzeText(lines: List<String>): List<String> {
        val totalLines = lines.size

        val words = lines.flatMap { it.split("\\s+".toRegex()) }
            .filter { it.isNotBlank() }

        val totalWords = words.size
        val uniqueWords = words.map { it.lowercase() }.toSet().size

        val totalLength = words.sumOf { it.length }
        val avgLength = if (words.isNotEmpty()) totalLength.toDouble() / totalWords else 0.0

        return listOf(
            "Общее количество строк: $totalLines",
            "Общее количество слов: $totalWords",
            "Уникальные слова: $uniqueWords",
            "Средняя длина слов: ${"%.2f".format(avgLength)}"
        )
    }
}
