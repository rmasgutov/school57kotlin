package ru.tbank.education.school.lection.files.nio

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val pathName = "example.txt"
    val path = Paths.get(pathName)

    if (Files.notExists(path)) {
        Files.createFile(path)
        println("Файл создан: $path")
    } else {
        println("Файл уже существует.")
    }
}