package ru.tbank.education.school.lesson7.task2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task2.safeCall

class SafeCallTest {

    @Test
    fun `возвращает Success при успешном выполнении`() {
        val divide = { n: Int -> 10 / n.coerceAtLeast(1) } // защита от деления на 0
        val safeDivide = safeCall(divide)

        val result = safeDivide(2)

        assertTrue(result.isSuccess, "Результат должен быть успешным")
        assertEquals(5, result.getOrNull())
    }

    @Test
    fun `возвращает Failure при исключении`() {
        val riskyDivide = { n: Int -> 10 / n }
        val safeDivide = safeCall(riskyDivide)

        val result = safeDivide(0)

        assertTrue(result.isFailure, "Результат должен содержать ошибку")
        assertInstanceOf(ArithmeticException::class.java, result.exceptionOrNull())
    }

    @Test
    fun `работает с функцией, возвращающей строки`() {
        val parseInt = { s: String -> s.toInt() }
        val safeParse = safeCall(parseInt)

        val ok = safeParse("42")
        val bad = safeParse("abc")

        assertTrue(ok.isSuccess)
        assertEquals(42, ok.getOrNull())

        assertTrue(bad.isFailure)
        assertInstanceOf(NumberFormatException::class.java, bad.exceptionOrNull())
    }

    @Test
    fun `должен возвращать корректный тип результата`() {
        val lengthFn = { s: String -> s.length }
        val safeLength = safeCall(lengthFn)

        val result = safeLength("Hello")

        assertEquals(Result.success(5), result)
    }

    @Test
    fun `не должен изменять поведение при повторных вызовах`() {
        var callCount = 0
        val fn = { n: Int ->
            callCount++
            if (n < 0) error("negative")
            n * 2
        }
        val safeFn = safeCall(fn)

        val r1 = safeFn(2)
        val r2 = safeFn(-1)
        val r3 = safeFn(3)

        assertTrue(r1.isSuccess)
        assertTrue(r2.isFailure)
        assertTrue(r3.isSuccess)
        assertEquals(3, callCount, "Функция должна вызываться каждый раз")
    }
}
