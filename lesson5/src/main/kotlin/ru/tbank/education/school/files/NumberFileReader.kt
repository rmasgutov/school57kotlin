package ru.tbank.education.school.files

import java.io.File
import java.io.FileWriter
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
        val file = File(path)
        val content = file.readText()
        return content.trim().toInt()
    }

}


class SaveNumberFileWriter(private val path: String) {
    fun readNumber(): Int? {
        try {
            val file = File(path)
            return file.readText().toInt()
        }
        catch (e: Exception) {}
        return null
    }
}