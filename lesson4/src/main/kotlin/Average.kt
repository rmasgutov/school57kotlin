@file:Suppress("UNREACHABLE_CODE")

package ru.tbank.education.school

/**
 * Класс для вычисления среднего арифметического.
 */
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object Average {
    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java IO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */

    fun processFileIO(source: String, target: String) {
        TODO()
        try {
            val inputFileIO = File(source)
            val outputFileIO = File(target)
            val lines = inputFileIO.readLines()
            val meanLines = lines.map { line ->
                val numbers = line.split(" ").map { it.toInt() }
                if (numbers.isNotEmpty()) numbers.sum() / numbers.size
                else 0
            }
            outputFileIO.writeText(meanLines.joinToString("\n"))
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
        TODO()
        try {
            val inputFileNIO = Paths.get(source)
            val outputFileNIO = Paths.get(target)
            val lines = Files.readAllLines(inputFileNIO)
            val meanLines = lines.map { line ->
                val numbers = line.split(" ").map { it.toInt() }
                if (numbers.isNotEmpty()) numbers.sum() / numbers.size
                else 0
            }
            Files.write(outputFileNIO, meanLines.joinToString("\n").toByteArray())
        } catch (e: FileNotFoundException) {
            println("Такого файла не существует: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлом(и): ${e.message}")
        }
        catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }
}