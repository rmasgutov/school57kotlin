package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NoPropertyClassTest {
    @DocClass(description = "Сервис уведомлений")
    class NotificationService {

        @DocMethod(description = "Отправляет email", returns = "true если отправлено")
        fun sendEmail(
            @DocParam(description = "Адрес получателя") to: String,
            @DocParam(description = "Тема письма") subject: String
        ) = true

        @DocMethod(description = "Отправляет SMS")
        fun sendSms(@DocParam(description = "Номер телефона") phone: String) = Unit
    }

    @Test
    fun `должен работать с классом без свойств`() {
        val service = NotificationService()
        val doc = DocumentationGenerator.generateDoc(service)

        assertTrue(doc.contains("Сервис уведомлений"))
        assertFalse(doc.contains("--- Свойства ---"), "Раздел свойств не должен появляться")
        assertTrue(doc.contains("Отправляет email"))
        assertTrue(doc.contains("Адрес получателя"))
        assertTrue(doc.contains("sendSms"))
    }
}