package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NoFunctionsClassTest {
    @DocClass(description = "Конфигурация приложения")
    class AppConfig(
        @DocProperty(description = "URL базы данных", example = "jdbc:sqlite:app.db")
        val dbUrl: String,

        @DocProperty(description = "Режим разработки")
        val devMode: Boolean
    )

    @Test
    fun `должен работать с классом без методов`() {
        val config = AppConfig("jdbc:sqlite:test.db", true)
        val doc = DocumentationGenerator.generateDoc(config)

        assertTrue(doc.contains("Конфигурация приложения"))
        assertTrue(doc.contains("URL базы данных"))
        assertTrue(doc.contains("Пример: jdbc:sqlite:app.db"))
        assertTrue(doc.contains("Режим разработки"))
        assertFalse(doc.contains("Методы"), "Раздел 'Методы' не должен появляться")
        assertFalse(doc.contains("--- Методы ---"), "Заголовок методов не должен быть")
    }
}