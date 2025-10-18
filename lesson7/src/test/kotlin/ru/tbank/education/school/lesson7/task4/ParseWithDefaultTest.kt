package ru.tbank.education.school.lesson7.task4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task4.parseWithDefault

class ParseWithDefaultTest {

    @Test
    fun `returns parsed number when input is valid`() {
        val result = parseWithDefault("123", 0)
        assertEquals(123, result)
    }

    @Test
    fun `returns default when input is not a number`() {
        val result = parseWithDefault("abc", 99)
        assertEquals(99, result)
    }

    @Test
    fun `returns default when input is empty string`() {
        val result = parseWithDefault("", -1)
        assertEquals(-1, result)
    }

    @Test
    fun `returns default when input contains spaces`() {
        val result = parseWithDefault("   ", 10)
        assertEquals(10, result)
    }

    @Test
    fun `parses negative numbers correctly`() {
        val result = parseWithDefault("-42", 0)
        assertEquals(-42, result)
    }

    @Test
    fun `returns default for large invalid input`() {
        val result = parseWithDefault("999999999999999999999", 123)
        assertEquals(123, result)
    }
}
