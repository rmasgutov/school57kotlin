package ru.tbank.education.school.lesson7.task4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task4.safeDivideLogged
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class SafeDivideLoggedTest {

    @Test
    fun `returns success when division is valid`() {
        val result = safeDivideLogged(10, 2)

        assertTrue(result.isSuccess)
        assertEquals(5.0, result.getOrNull())
    }

    @Test
    fun `handles negative division correctly`() {
        val result = safeDivideLogged(-9, 3)

        assertTrue(result.isSuccess)
        assertEquals(-3.0, result.getOrNull())
    }

    @Test
    fun `handles floating point result correctly`() {
        val result = safeDivideLogged(7, 2)

        assertTrue(result.isSuccess)
        assertEquals(3.5, result.getOrNull())
    }
}
