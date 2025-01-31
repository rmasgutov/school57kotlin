package ru.tbank.education.school

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

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
        val fileIn = File(source)
        try {
            val streamIn = FileInputStream(fileIn)
            val bytes = streamIn.readAllBytes()
            streamIn.close()

            val string = String(bytes)
            val lines = string.lines().toMutableList()
            var cnt = 0
            for (line in lines) {
                try {
                    val numbers = line.split(" ").map { it.toInt() }
                    val sumOfNumbers = numbers.sum()
                    val countOfNumbers = numbers.size.toDouble()
                    val average = sumOfNumbers / countOfNumbers
                    val fileOut = File(target)
                    try {
                        val streamOut = FileOutputStream(fileOut, true)
                        streamOut.use {
                            val output = if (cnt == 0) average.toString() else " $average"
                            it.write(output.toByteArray())
                        }
                        cnt += 1
                    } catch (e: IOException) {
                        println("An error occurred while writing to the file: ${e.message}")
                        break
                    }
                } catch (e: NumberFormatException) {
                    println("Invalid number format in line: $line")
                    break
                }
            }
        } catch (e: IOException) {
            println("Source file does not exist: $source")
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
            val lines = Files.readAllLines(Paths.get(source))
            var cnt = 0
            for (line in lines) {
                try {
                    val numbers = line.split(" ").map { it.toInt() }
                    val sumOfNumbers = numbers.sum()
                    val countOfNumbers = numbers.size.toDouble()
                    val average = sumOfNumbers / countOfNumbers
                    try {
                        Files.write(Paths.get(target), "${if (cnt == 0) average else " " + average.toString()}".toByteArray(), StandardOpenOption.APPEND)
                        cnt += 1
                    } catch (e: IOException) {
                        println("An error occurred while writing to the file: ${e.message}")
                        break
                    }
                } catch (e: NumberFormatException) {
                    println("Invalid number format in line: $line")
                    break
                }
            }
            if (lines.size == 0) {
                println("Invalid number format in line:")
            }
        } catch (e: IOException) {
            println("Source file does not exist: $source")
        }
    }
}
