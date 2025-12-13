package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DataClassDefaultMethodTest {
    @DocClass(description = "Пользователь (data class)")
    data class UserData(
        @DocProperty(description = "Имя пользователя") val name: String,
        @DocProperty(description = "Возраст", example = "18") val age: Int
    )

    @Test
    fun `должен игнорировать автоматические методы data class`() {
        val user = UserData("Alice", 30)
        val doc = DocumentationGenerator.generateDoc(user)

        assertTrue(doc.contains("Пользователь (data class)"))
        assertTrue(doc.contains("Имя пользователя"))
        assertTrue(doc.contains("Возраст"))

        // Проверяем, что основные методы data class не попали в документацию
        assertFalse(doc.contains("equals"), "equals() не должен быть в документации")
        assertFalse(doc.contains("hashCode"), "hashCode() не должен быть в документации")
        assertFalse(doc.contains("toString"), "toString() не должен быть в документации")
        assertFalse(doc.contains("copy"), "copy() не должен быть в документации")
        assertFalse(doc.contains("component1"), "component1() не должен быть в документации")
        assertFalse(doc.contains("component2"), "component2() не должен быть в документации")
    }
}