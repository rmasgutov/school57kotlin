package ru.tbank.education.school.lesson7.task2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task2.limitRate

class LimitRateTest {

    @Test
    fun `выполняет функцию при первом вызове`() {
        var executed = false
        val limited = limitRate(1000L) { x: Int ->
            executed = true
            x * 2
        }

        val result = limited(5)
        assertTrue(executed, "Функция должна выполниться при первом вызове")
        assertEquals(10, result)
    }

    @Test
    fun `пропускает вызов если интервал не истек`() {
        var callCount = 0
        val limited = limitRate(1000L) { msg: String ->
            callCount++
            msg
        }

        val first = limited("A")
        val second = limited("B")

        assertEquals(1, callCount, "Функция должна выполниться только один раз")
        assertEquals("A", first)
        assertNull(second, "Второй вызов должен быть пропущен (слишком рано)")
    }

    @Test
    fun `выполняет снова после истечения интервала`() {
        var callCount = 0
        val limited = limitRate(300L) { msg: String ->
            callCount++
            msg
        }

        val first = limited("A")
        Thread.sleep(400) // ждём больше, чем интервал
        val second = limited("B")

        assertEquals(2, callCount, "Функция должна выполниться дважды")
        assertEquals("A", first)
        assertEquals("B", second)
    }

    @Test
    fun `несколько быстрых вызовов подряд выполняются только один раз`() {
        var callCount = 0
        val limited = limitRate(500L) { id: Int ->
            callCount++
            id
        }

        repeat(5) { limited(it) } // подряд без паузы

        assertEquals(1, callCount, "Из пяти быстрых вызовов должен выполниться только первый")
    }
}