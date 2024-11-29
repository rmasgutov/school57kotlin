package ru.tbank.education.school.lesson4

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Disabled
class RenameFileExample {

    @Test
    fun `rename file IO`() {
        val source = File("src/test/resources/1.txt")
        source.renameTo(File("src/test/resources/2.txt"))
    }

    @Test
    fun `rename file NIO`() {
        val source = Paths.get("src/test/resources/2.txt")
        val target = Paths.get("src/test/resources/1.txt")
        Files.move(source, target)
    }
}
