package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MethodWithoutAnnotationsTest {
    @DocClass(description = "Класс с простым методом")
    class SimpleService {
        fun greet(name: String, times: Int): String {
            return "Hello $name ".repeat(times)
        }
    }

    @Test
    fun `должен документировать метод без аннотаций`() {
        val doc = DocumentationGenerator.generateDoc(SimpleService())

        assertTrue(doc.contains("greet"))
        assertTrue(doc.contains("name: Нет описания"))
        assertTrue(doc.contains("times: Нет описания"))
        assertTrue(doc.contains("Возвращает: Нет описания"))
    }
}