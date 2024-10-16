package ru.tbank.education.school

import java.nio.file.Files
import java.nio.file.Paths
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

object Average {

    fun processFileIO(source: String, target: String) {
        try {
            val inputFileIo = File(source)
            val outputFileIo = File(target)

            val lines = inputFileIo.readLines()
            val listAverage: MutableList<Double> = mutableListOf()
            for (line in lines) {
                val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
                if (numbers.isNotEmpty()) {
                    listAverage.add(numbers.average())
                }
                else {
                    listAverage.add(0.0)
                }
            }
            outputFileIo.writeText(listAverage.joinToString(" "))
        } catch (e: FileNotFoundException) {
            println("Файл не найден: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        } catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }
    fun processFileNIO(source: String, target: String) {
        try {
            val inputFileNIO = Paths.get(source)
            val outputFileNIO = Paths.get(target)

            val listAverage: MutableList<Double> = mutableListOf()
            val lines = Files.readAllLines(inputFileNIO)
            for (line in lines) {
                val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
                if (numbers.isNotEmpty()) {
                    listAverage.add(numbers.average())
                }
                else {
                    listAverage.add(0.0)
                }
            }
            Files.write(outputFileNIO, listAverage.joinToString(" ").toByteArray())
        }
        catch (e: java.nio.file.NoSuchFileException) {
            println("Файл не найден: ${e.message}")
        }
        catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        }
        catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }
}