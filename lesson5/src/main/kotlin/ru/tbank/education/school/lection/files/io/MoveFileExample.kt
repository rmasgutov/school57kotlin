package ru.tbank.education.school.lection.files.io

import java.io.File

fun main() {
    val filePath = "example.txt"
    val newFilePath = "new_location/rename_example.txt"


    val oldFile = File(filePath)
    val newFile = File(newFilePath)

    if (!newFile.parentFile.exists()) {
        newFile.parentFile.mkdirs()
    }

    if (oldFile.renameTo(newFile)) {
        println("Файл перемещён и переименован: ${newFile.absolutePath}")
    } else {
        println("Не удалось переместить файл.")
    }
}