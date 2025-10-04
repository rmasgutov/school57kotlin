package ru.tbank.education.school.lection.files.nio

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

fun main() {
    val filePath = "example.txt"
    val newFilePath = "new_location/rename_example.txt"


    val source = Paths.get(filePath)
    val target = Paths.get(newFilePath)

    if (!target.parent.toFile().exists()) {
        target.parent.toFile().mkdirs()
    }

    if (Files.exists(source)) {
        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING)
        println("Файл перемещён и переименован: $target")
    } else {
        println("Исходный файл не найден.")
    }
}