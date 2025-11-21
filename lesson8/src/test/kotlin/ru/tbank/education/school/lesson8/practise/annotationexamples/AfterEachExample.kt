package ru.tbank.education.school.lesson8.practise.annotationexamples

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

class AfterEachExample {
    private val file = File("test.txt")

    @AfterEach
    fun cleanUp() {
        if (file.exists()) {
            file.delete()
            println("Файл удален после теста")
        }
    }

    @Test
    fun `create test file`() {
        file.writeText("test content")
        assertTrue(file.exists())
    }
}
