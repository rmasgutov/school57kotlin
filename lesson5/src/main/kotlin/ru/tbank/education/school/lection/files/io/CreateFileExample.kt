package ru.tbank.education.school.lection.files.io

import java.io.File

fun main() {
    val pathName = "example.txt"
    val file = File(pathName)

    if (file.createNewFile()) {
        println("Файл создан: ${file.absolutePath}")
    } else {
        println("Файл уже существует.")
    }
}