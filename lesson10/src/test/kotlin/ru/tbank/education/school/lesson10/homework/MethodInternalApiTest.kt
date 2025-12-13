package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class MethodInternalApiTest {
    @DocClass(description = "Сервис с внутренним методом")
    class InternalMethodService {
        @InternalApi
        fun internalCalc(x: Int): Int = x * 2

        fun publicAction() = "done"
    }

    @Test
    fun `должен скрыть метод с @InternalApi`() {
        val doc = DocumentationGenerator.generateDoc(InternalMethodService())

        assertTrue(doc.contains("publicAction"))
        assertFalse(doc.contains("internalCalc"), "@InternalApi метод должен быть скрыт")
    }
}