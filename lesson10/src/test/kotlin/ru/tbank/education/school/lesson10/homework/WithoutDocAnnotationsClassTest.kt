package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WithoutDocAnnotationsClassTest {
    class User(val name: String)

    @Test
    fun `должен вернуть сообщение, если нет @DocClass`() {
        val doc = DocumentationGenerator.generateDoc(User("Test"))
        assertEquals("Нет документации для класса.", doc)
    }
}