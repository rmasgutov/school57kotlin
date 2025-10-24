package ru.tbank.education.school.lesson7.task3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task3.inRange

class InRangeTest {

    @Test
    fun `returns true when value inside range`() {
        val age = 25
        assertTrue(age inRange 18..30)
    }

    @Test
    fun `returns false when value below range`() {
        val age = 10
        assertFalse(age inRange 18..30)
    }

    @Test
    fun `returns false when value above range`() {
        val age = 35
        assertFalse(age inRange 18..30)
    }

    @Test
    fun `returns true on lower bound`() {
        val age = 18
        assertTrue(age inRange 18..30)
    }

    @Test
    fun `returns true on upper bound`() {
        val age = 30
        assertTrue(age inRange 18..30)
    }

    @Test
    fun `works with single value range`() {
        val age = 42
        assertTrue(age inRange 42..42)
        assertFalse(age inRange 43..43)
    }
}
