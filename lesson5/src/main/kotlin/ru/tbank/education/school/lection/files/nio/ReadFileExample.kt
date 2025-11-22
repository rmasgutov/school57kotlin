package ru.tbank.education.school.lection.files.nio

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val filePath = "example.txt"

    val lines = Files.readAllLines(Paths.get(filePath))
    println("Содержимое файла:")
    lines.forEach { println(it) }
}