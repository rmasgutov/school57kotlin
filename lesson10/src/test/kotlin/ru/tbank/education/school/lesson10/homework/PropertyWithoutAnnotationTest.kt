package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PropertyWithoutAnnotationTest {
    @DocClass(description = "Класс с частичной документацией")
    class PartialDoc(
        val id: Int,
        @DocProperty(description = "Имя") val name: String
    )

    @Test
    fun `должен показать свойства без описания`() {
        val doc = DocumentationGenerator.generateDoc(PartialDoc(1, "Test"))

        assertTrue(doc.contains("id"))
        assertFalse(doc.contains("Пример"), "id не имеет @DocProperty")
        assertTrue(doc.contains("name"))
        assertTrue(doc.contains("Имя"))
    }
}