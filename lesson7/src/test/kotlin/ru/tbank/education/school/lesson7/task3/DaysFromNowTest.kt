package ru.tbank.education.school.lesson7.task3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task3.daysFromNow
import java.time.LocalDate

class DaysFromNowTest {

    @Test
    fun `adds days to current date`() {
        val today = LocalDate.now()
        val result = 5 daysFromNow Unit

        assertEquals(today.plusDays(5), result)
    }

    @Test
    fun `works with zero days`() {
        val today = LocalDate.now()
        val result = 0 daysFromNow Unit

        assertEquals(today, result)
    }

    @Test
    fun `works with negative days`() {
        val today = LocalDate.now()
        val result = (-3) daysFromNow Unit

        assertEquals(today.minusDays(3), result)
    }

    @Test
    fun `chained calls produce independent results`() {
        val today = LocalDate.now()
        val first = 2 daysFromNow Unit
        val second = 5 daysFromNow Unit

        assertEquals(today.plusDays(2), first)
        assertEquals(today.plusDays(5), second)
    }
}
