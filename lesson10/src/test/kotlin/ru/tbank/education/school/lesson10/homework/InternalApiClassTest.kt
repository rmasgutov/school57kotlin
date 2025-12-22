package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InternalApiClassTest {
    @InternalApi
    @DocClass(description = "Скрытый класс")
    class SecretService {
        fun doWork() = "secret"
    }

    @Test
    fun `должен скрыть класс с @InternalApi`() {
        val doc = DocumentationGenerator.generateDoc(SecretService())
        assertEquals("Документация скрыта (InternalApi).", doc)
    }
}