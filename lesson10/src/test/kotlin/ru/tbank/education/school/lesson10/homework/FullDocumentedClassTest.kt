package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FullDocumentedClassTest {

    // Полностью задокументированный класс
    @DocClass(description = "Модель пользователя", author = "Bob", version = "1.5")
    class User(
        @DocProperty(description = "Уникальный идентификатор", example = "12345")
        val id: Int,

        @DocProperty(description = "Имя пользователя", example = "alice")
        var name: String
    ) {
        @DocMethod(description = "Проверяет, совершеннолетний ли пользователь", returns = "true если возраст >= 18")
        fun isAdult(@DocParam(description = "Текущий год") currentYear: Int): Boolean {
            return currentYear - 2000 >= 18
        }
    }

    @Test
    fun `должен документировать полностью аннотированный класс`() {
        val user = User(1, "Alice")
        val doc = DocumentationGenerator.generateDoc(user)

        assertTrue(doc.contains("Модель пользователя"))
        assertTrue(doc.contains("Автор: Bob"))
        assertTrue(doc.contains("id"))
        assertTrue(doc.contains("Уникальный идентификатор"))
        assertTrue(doc.contains("Пример: 12345"))
        assertTrue(doc.contains("isAdult"))
        assertTrue(doc.contains("Проверяет, совершеннолетний ли пользователь"))
        assertTrue(doc.contains("Текущий год"))
    }
}