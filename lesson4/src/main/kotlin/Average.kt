package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths


object Average {

    fun processFileIO(source: String, target: String) {
        try {
            val data = File(source)

            val ans = File(target)

            val lines = data.readLines()

            val averages = countAverage(lines)

            ans.writeText(averages.joinToString("\n"))
        } catch (e: NumberFormatException) {
            println("Ошибка формата данных: ${e.message}")
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

            val averages = countAverage(lines)

            Files.write(ans, averages.joinToString("\n").toByteArray())
        } catch (e: NumberFormatException) {
            println("Ошибка формата данных: ${e.message}")
        } catch (e: FileNotFoundException) {
            println("Файл не найден: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка во время работы с файлом: ${e.message}")
        } catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }

    }
    fun countAverage(lines: List<String>): List<Double> {
        val averages = lines.map { line ->

            if (line.isBlank()) 0.0
            else {
                val numbers = line.split("\\s+".toRegex()).map { it.toDouble() }
                numbers.average()
            }
        }
        return averages
    }
}