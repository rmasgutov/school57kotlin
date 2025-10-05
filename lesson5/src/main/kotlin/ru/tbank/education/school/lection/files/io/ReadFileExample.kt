package ru.tbank.education.school.lection.files.io

import java.io.BufferedReader
import java.io.FileReader

fun main() {
    val path = "example.txt"
    val reader = BufferedReader(FileReader(path))
    reader.use {
        println("Содержимое файла:")
        var line: String?
        while (it.readLine().also { line = it } != null) {
            println(line)
        }
    }
}