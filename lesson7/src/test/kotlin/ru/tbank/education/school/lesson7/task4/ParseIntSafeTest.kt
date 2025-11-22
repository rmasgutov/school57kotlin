package ru.tbank.education.school.lesson7.task4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task4.parseIntSafe

class ParseIntSafeTest {

    @Test
    fun `returns success for valid integer`() {
        val result = parseIntSafe("123")

        assertTrue(result.isSuccess)
        assertEquals(123, result.getOrNull())
    }

    @Test
    fun `returns failure for non-numeric string`() {
        val result = parseIntSafe("abc")

        assertTrue(result.isFailure)
        assertThrows(NumberFormatException::class.java) {
            result.getOrThrow()
        }
    }

    @Test
    fun `returns failure for empty string`() {
        val result = parseIntSafe("")

        assertTrue(result.isFailure)
        assertThrows(NumberFormatException::class.java) {
            result.getOrThrow()
        }
    }

    @Test
    fun `returns failure for whitespace string`() {
        val result = parseIntSafe("  ")

        assertTrue(result.isFailure)
    }

    @Test
    fun `parses negative number correctly`() {
        val result = parseIntSafe("-42")

        assertTrue(result.isSuccess)
        assertEquals(-42, result.getOrNull())
    }
}
