package ru.tbank.education.school.lection.files.io

import java.io.BufferedReader
import java.io.FileReader

fun main() {
    val path = "C:\\Users\\Admin\\OneDrive\\Документы\\буткемп\\ляля.txt"
    val reader = BufferedReader(FileReader(path))
    reader.use {
        println("Содержимое файла:")
        var line: String?
        var a:Int
        a=0
        while (it.readLine().also { line = it } != null) {
            println(line)
            a=a+1
        }
        print(a)
    }
}