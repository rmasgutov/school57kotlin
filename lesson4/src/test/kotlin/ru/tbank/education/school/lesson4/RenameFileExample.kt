package ru.tbank.education.school.lesson4

import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class RenameFileExample {

    @Test
    fun `rename file IO`() {
        val source = File("src/test/resources/1.txt")
        val target = File("src/test/resources/2.txt")
        
        if (source.exists()) {
            val renamed = source.renameTo(target)
            if (renamed) {
                println("File renamed successfully.")
            } else {
                println("Failed to rename file.")
            }
        } else {
            println("Source file does not exist.")
        }
    }

    @Test
    fun `rename file NIO`() {
        val source = Paths.get("src/test/resources/2.txt")
        val target = Paths.get("src/test/resources/1.txt")
        
        if (Files.exists(source)) {
            val renamed = Files.move(source, target)
            if (renamed != null) {
                println("File renamed successfully.")
            } else {
                println("Failed to rename file.")
            }
        } else {
            println("Source file does not exist.")
        }
    }
}
