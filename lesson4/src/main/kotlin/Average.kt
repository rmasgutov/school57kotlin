package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Paths
import kotlin.io.path.appendText
import java.nio.file.Files

object Average {
    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java IO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileIO(source: String, target: String) {
        try {
            var inputfileio = File(source)
            var listav: MutableList<Double> = mutableListOf()
            var lines = inputfileio.readLines()
            val outputfileio = File(target)
            for (line in lines) {
                var line1 = line.split(" ").map { it.toInt() }
                var t = line1
                listav.add(t.average())
            }
            outputfileio.writeText(listav.joinToString(" "))
        } catch (e: FileNotFoundException) {
            println("Высосо, файл не найден ${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        } catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
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
        var inputnio = Paths.get(source)
        var listav: MutableList<Double> = mutableListOf()
        val lines = Files.readAllLines(inputnio)
        for (line in lines) {
            var line1 = line.split(" ").map {it.toInt()}
            var t = line1
            listav.add(t.average())
        }
        var outputnio = Paths.get(target)
        outputnio.appendText(listav.joinToString(" "))
    } catch (e: FileNotFoundException) {
        println("ЛОл, а такого файла нету:${e.message}")
    } catch (e: FileNotFoundException) {
        println("Ошибка при работе с файлами:${e.message}")
    } catch (e: FileNotFoundException) {
        println("Неизвестная ошибка:${e.message}")
    }
}



