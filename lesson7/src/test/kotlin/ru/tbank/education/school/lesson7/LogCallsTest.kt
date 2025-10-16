package ru.tbank.education.school.lesson7

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task2.logCalls
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class LogCallsTest {

    @Test
    fun `должен логировать аргумент и результат`() {
        val output = ByteArrayOutputStream()
        val originalOut = System.out
        System.setOut(PrintStream(output))

        try {
            val loggedAdd = logCalls("add") { n: Int -> n + 10 }
            val result = loggedAdd(5)

            val printed = output.toString().trim().lines()

            assertEquals(15, result, "Результат функции должен совпадать с исходным поведением")

            assertEquals("[add] вызвана с аргументом: 5", printed[0])
            assertEquals("[add] вернула результат: 15", printed[1])
        } finally {
            System.setOut(originalOut)
        }
    }

    @Test
    fun `должен корректно работать с разными типами`() {
        val output = ByteArrayOutputStream()
        val originalOut = System.out
        System.setOut(PrintStream(output))

        try {
            val loggedUpper = logCalls("toUpper") { s: String -> s.uppercase() }
            val result = loggedUpper("hello")

            val printed = output.toString().trim().lines()

            assertEquals("HELLO", result)
            assertEquals("[toUpper] вызвана с аргументом: hello", printed[0])
            assertEquals("[toUpper] вернула результат: HELLO", printed[1])
        } finally {
            System.setOut(originalOut)
        }
    }

    @Test
    fun `должен вызывать оригинальную функцию ровно один раз`() {
        var callCount = 0
        val loggedFn = logCalls("count") { n: Int ->
            callCount++
            n * 2
        }

        val result = loggedFn(4)

        assertEquals(1, callCount, "Оригинальная функция должна вызываться ровно один раз")
        assertEquals(8, result)
    }

    @Test
    fun `должен корректно работать при последовательных вызовах`() {
        val output = ByteArrayOutputStream()
        val originalOut = System.out
        System.setOut(PrintStream(output))

        try {
            val loggedAdd = logCalls("sum") { x: Int -> x + 1 }

            loggedAdd(1)
            loggedAdd(2)

            val printed = output.toString().trim().lines()

            assertEquals(
                listOf(
                    "[sum] вызвана с аргументом: 1",
                    "[sum] вернула результат: 2",
                    "[sum] вызвана с аргументом: 2",
                    "[sum] вернула результат: 3"
                ),
                printed
            )
        } finally {
            System.setOut(originalOut)
        }
    }
}
