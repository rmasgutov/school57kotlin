package ru.tbank.education.school.lection.files.io

import java.io.FileWriter

fun main() {
    val fileName = "example.txt"
    val writer = FileWriter(fileName)
    try {
        writer.write("Привет, мир!\nЗапись через java.io")
        println("Данные записаны в файл.")
    } finally {
        writer.close()
    }
}