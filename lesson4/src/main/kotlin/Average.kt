package ru.tbank.education.school

import java.io.*
import java.nio.file.*
import java.nio.file.StandardOpenOption

object Average {

    fun calculateIO(inputPath: String, outputPath: String) {
        try {
            BufferedReader(FileReader(inputPath)).use { reader ->
                BufferedWriter(FileWriter(outputPath)).use { writer ->
                    var line: String?

                    while (reader.readLine().also { line = it } != null) {
                        val parts = line!!.trim().split("\\s+".toRegex())
                            .filter { it.isNotEmpty() }

                        if (parts.isEmpty()) {
                            writer.write("0")
                            writer.newLine()
                            continue
                        }

                        try {
                            val sum = parts.sumOf { it.toInt() }
                            val avg = sum.toDouble() / parts.size
                            writer.write(avg.toString())
                            writer.newLine()
                        } catch (e: NumberFormatException) {
                            writer.write("NaN")
                            writer.newLine()
                        }
                    }
                }
            }
        } catch (e: IOException) {
            println("Ошибка при работе с файлами (IO): ${e.message}")
        }
    }

    fun calculateNIO(inputPath: String, outputPath: String) {
        val input = Paths.get(inputPath)
        val output = Paths.get(outputPath)

        try {
            val lines = Files.readAllLines(input)
            val results = mutableListOf<String>()

            for (line in lines) {
                val parts = line.trim().split("\\s+".toRegex())
                    .filter { it.isNotEmpty() }

                if (parts.isEmpty()) {
                    results.add("0")
                    continue
                }

                try {
                    val sum = parts.sumOf { it.toInt() }
                    val avg = sum.toDouble() / parts.size
                    results.add(avg.toString())
                } catch (e: NumberFormatException) {
                    results.add("NaN")
                }
            }

            Files.write(output, results, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
        } catch (e: IOException) {
            println("Ошибка при работе с файлами (NIO): ${e.message}")
        }
    }
}
