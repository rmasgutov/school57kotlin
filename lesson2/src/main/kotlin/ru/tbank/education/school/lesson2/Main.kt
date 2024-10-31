package ru.tbank.education.school.lesson3


import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths

class FileInteraction(val name: String) {

    fun printFile() {
        val file = File(name)
        val stream = FileInputStream(file)
        val bytes = stream.readAllBytes()
        val string = String(bytes)
        println(string)
    }

    fun writeFile(content: String) {
        val stream = FileOutputStream(name)
        stream.write(content.toByteArray())
    }

    fun appendFile(content: String) {
        val stream = FileOutputStream(name, true)
        stream.write(content.toByteArray())
    }

    fun createFile(clear: Boolean = false) {
        File(name).createNewFile()
        if (clear) {
            this.writeFile("")

        }
    }
}

fun main() {
    val file = FileInteraction("config.txt")
    file.createFile()
    file.printFile()
    file.appendFile("456789")
    file.printFile()
}