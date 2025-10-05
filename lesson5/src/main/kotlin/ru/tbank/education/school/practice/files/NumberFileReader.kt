package ru.tbank.education.school.practice.files

import java.io.File
import java.io.IOException


/**
 * Читает число из файла.
 *
 * Целевое поведение:
 * - Возвращать число, если файл существует и содержит корректное целое.
 * - Если файла нет или содержимое некорректно — вернуть null (не падать с исключением).
 */
class NumberFileReader(private val path: String) {

    fun readNumber(): Int? {
        try {
            val file = File(path)
            val content = file.readText()
            return content.trim().toInt()
        } catch (ex: IOException) {
            println("Ошибка IO: ${ex.message}")
            return null
        } catch (ex: NumberFormatException) {
            println("Ошибка приведения к целочисленному типу: ${ex.message}")
            return null
        }
    }

}

//fun main() {
//    val path = "./lesson5/numbers.txt"
//
//    val reader = NumberFileReader(path)
//    println(reader.readNumber())
//}