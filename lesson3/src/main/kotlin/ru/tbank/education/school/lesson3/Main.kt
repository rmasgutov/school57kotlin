package ru.tbank.education.school.lesson3


import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths

class FileInteraction(var name: String, val clear: Boolean = false) {
    init {
        createFile(clear)
    }

    fun printFile() {
        val file = File(name)
        val stream = FileInputStream(file)
        stream.use {
            val bytes = stream.readAllBytes()
            val string = String(bytes)
            println(string)
        }
    }

    fun writeFile(content: String) {
        val stream = FileOutputStream(name)
        stream.use {
            stream.write(content.toByteArray())
        }
    }

    fun appendFile(content: String) {
        val stream = FileOutputStream(name, true)
        stream.use {
            stream.write(content.toByteArray())
        }
    }

    fun createFile(clear: Boolean = false) {
        File(name).createNewFile()
        if (clear) {
            writeFile("")
        }
    }

    fun move(to: String) {
        val source = Paths.get(name)
        val target = Paths.get(to)
        Files.move(source, target)
        name = to
    }
}

fun main() {
    val file = FileInteraction("config.txt", true)
    file.printFile()
    file.appendFile("456789")
    file.printFile()
    file.move("test.txt")
}
