package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PartialAnnotatedClassTest {
    @DocClass(description = "Частично аннотированный класс")
    class PartialAnnotations(
        @DocProperty(description = "ID") val id: Int,
        val name: String  // без аннотации
    ) {
        @DocMethod(description = "Простой метод без параметров")
        fun ping(): String = "pong"

        fun unknown(): Int = 42  // без аннотаций
    }

    @Test
    fun `должен обрабатывать частично аннотированные элементы`() {
        val obj = PartialAnnotations(1, "Test")
        val doc = DocumentationGenerator.generateDoc(obj)

        // Свойства
        assertTrue(doc.contains("id"))
        assertTrue(doc.contains("Описание: ID"))
        assertTrue(doc.contains("name"))  // отображается, но без описания

        // Методы
        assertTrue(doc.contains("ping"))
        assertTrue(doc.contains("Простой метод без параметров"))
        assertTrue(doc.contains("unknown"))  // отображается, но без описания
        assertTrue(doc.contains("Возвращает: Нет описания"))
    }
}