package ru.tbank.education.school.lesson8.practise.annotationexamples

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class BeforeAfterAllExample {
    companion object {
        private lateinit var databaseConnection: String

        @BeforeAll
        @JvmStatic
        fun setUpAll() {
            databaseConnection = "Connected to DB"
            println("Установлено соединение с базой данных")
        }

        @AfterAll
        @JvmStatic
        fun tearDownAll() {
            databaseConnection = "Disconnected"
            println("Соединение с базой данных закрыто")
        }
    }

    @Test
    fun `test database connection`() {
        assertEquals("Connected to DB", databaseConnection)
    }
}
