package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class InternalApiPropertyClassTest {
    @DocClass(description = "Класс с секретным полем")
    class UserWithSecret(
        @DocProperty(description = "Имя") val name: String,

        @InternalApi
        @DocProperty(description = "Токен (скрыт)") val token: String
    ) {
        fun getName(token: String) = name
    }

    @Test
    fun `должен скрыть свойство с @InternalApi`() {
        val user = UserWithSecret("Alice", "secret123")
        val doc = DocumentationGenerator.generateDoc(user)

        assertTrue(doc.contains("Имя"))
        assertFalse(doc.contains("token"), "@InternalApi свойство должно быть скрыто")
        assertFalse(doc.contains("Токен"), "Описание тоже не должно быть видно")
    }
}