package ru.tbank.education.school.lesson7.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task1.Event
import ru.tbank.education.school.lesson7.practise.task1.EventType
import ru.tbank.education.school.lesson7.practise.task1.sliceEvents
import java.time.LocalDate
import java.time.LocalDateTime

class SliceEventsTest {

    @Test
    fun `returns correct slices`() {
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)

        val events = listOf(
            Event(EventType.INFO, LocalDateTime.of(yesterday, java.time.LocalTime.NOON)),
            Event(EventType.LOGIN, LocalDateTime.of(yesterday, java.time.LocalTime.of(14, 0))),
            Event(EventType.ERROR, LocalDateTime.of(yesterday, java.time.LocalTime.of(15, 0))),
            Event(EventType.LOGIN, LocalDateTime.of(today, java.time.LocalTime.of(10, 0))),
            Event(EventType.LOGOUT, LocalDateTime.of(today, java.time.LocalTime.of(11, 0))),
            Event(EventType.LOGIN, LocalDateTime.of(today, java.time.LocalTime.of(12, 0))),
            Event(EventType.INFO, LocalDateTime.of(today, java.time.LocalTime.of(13, 0)))
        )

        val (firstError, lastTwoLogins, firstToday) = sliceEvents(events, 2)

        // 1️⃣ Проверка первого ERROR
        assertEquals(EventType.ERROR, firstError?.type)

        // 2️⃣ Проверка последних двух LOGIN
        assertEquals(2, lastTwoLogins.size)
        assertEquals(listOf(EventType.LOGIN, EventType.LOGIN), lastTwoLogins.map { it.type })
        assertTrue(lastTwoLogins[0].date.isBefore(lastTwoLogins[1].date))

        // 3️⃣ Проверка первых N событий за сегодня
        assertEquals(2, firstToday.size)
        assertTrue(firstToday.all { it.date.toLocalDate() == today })
        assertEquals(events[3], firstToday[0]) // LOGIN @10:00
        assertEquals(events[4], firstToday[1]) // LOGOUT @11:00
    }

    @Test
    fun `handles empty list`() {
        val result = sliceEvents(emptyList(), 3)
        assertNull(result.first)
        assertTrue(result.second.isEmpty())
        assertTrue(result.third.isEmpty())
    }

    @Test
    fun `handles no errors or logins`() {
        val today = LocalDate.now()
        val events = listOf(
            Event(EventType.INFO, LocalDateTime.of(today, java.time.LocalTime.NOON))
        )

        val (firstError, lastTwoLogins, todayEvents) = sliceEvents(events, 1)

        assertNull(firstError)
        assertTrue(lastTwoLogins.isEmpty())
        assertEquals(1, todayEvents.size)
    }
}

