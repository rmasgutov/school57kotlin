package ru.tbank.education.school.lesson7

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task2.timed
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TimedDecoratorTest {

    @Test
    fun `должен измерять и логировать время выполнения`() {
        val output = ByteArrayOutputStream()
        val originalOut = System.out
        System.setOut(PrintStream(output))

        try {
            val slowFn = timed("Slow sum") { n: Int ->
                Thread.sleep(100)
                n * 2
            }

            val result = slowFn(5)

            assertEquals(10, result, "Функция должна возвращать результат исходной функции")

            val printed = output.toString().trim()

            assertTrue(
                printed.startsWith("[Slow sum] выполнено за"),
                "Лог должен начинаться с '[Slow sum] выполнено за'"
            )

            // Извлекаем число миллисекунд
            val duration = printed.substringAfter("за ").substringBefore(" мс").toLongOrNull()
            assertNotNull(duration, "Время выполнения должно быть числом")
            assertTrue(duration!! >= 90L, "Ожидается, что длительность будет хотя бы 90 мс (sleep 100)")
        } finally {
            System.setOut(originalOut)
        }
    }

    @Test
    fun `должен вызывать исходную функцию ровно один раз`() {
        var callCount = 0
        val timedFn = timed("Counter") { n: Int ->
            callCount++
            n + 1
        }

        val result = timedFn(10)

        assertEquals(1, callCount, "Функция должна вызываться ровно один раз")
        assertEquals(11, result)
    }

    @Test
    fun `должен корректно работать с любыми типами`() {
        val timedConcat = timed("Concat") { s: String ->
            Thread.sleep(50)
            s + "!"
        }

        val output = ByteArrayOutputStream()
        val originalOut = System.out
        System.setOut(PrintStream(output))

        try {
            val result = timedConcat("Hi")

            assertEquals("Hi!", result)
            val printed = output.toString().trim()
            assertTrue(printed.contains("[Concat] выполнено за"), "Ожидается вывод таймера в консоль")
        } finally {
            System.setOut(originalOut)
        }
    }
}
