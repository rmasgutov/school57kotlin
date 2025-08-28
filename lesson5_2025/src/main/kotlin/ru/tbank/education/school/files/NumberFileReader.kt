package ru.tbank.education.school.files

import java.io.File


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