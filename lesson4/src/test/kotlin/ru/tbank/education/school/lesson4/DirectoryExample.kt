package ru.tbank.education.school.lesson4

import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class DirectoryExample {
    @Test
    fun `show directory`() {
        Files.walk(Paths.get("src/test/resources"))
            .forEach { println(it.fileName) }
    }
}
