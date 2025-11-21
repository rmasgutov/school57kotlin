package ru.tbank.education.school.lesson8.practise.annotationexamples

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BasicTest {

    @Test
    fun `simple addition test`() {
        val result = 2 + 2
        assertEquals(4, result)
    }

    @Test
    fun `string length test`() {
        val name = "Kotlin"
        assertEquals(6, name.length)
    }
}
