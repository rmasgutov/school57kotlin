package ru.tbank.education.school.lection.files.nio

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

fun main() {
    val path = "example.txt"
    val content = "Привет, мир!\nЗапись через java.nio"

    Files.write(
        Paths.get(path), content.toByteArray(),
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING
    )
    println("Данные записаны в файл.")
}