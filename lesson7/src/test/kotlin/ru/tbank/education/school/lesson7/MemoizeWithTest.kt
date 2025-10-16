package ru.tbank.education.school.lesson7

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task2.memoizeWith

class MemoizeWithTest {

    @Test
    fun `должен кэшировать результаты вызовов`() {
        var callCount = 0
        val f = { x: Int ->
            callCount++
            x * x
        }

        val cached = memoizeWith(3, f)

        // первый вызов вычисляет
        val r1 = cached(2)
        // второй — берёт из кэша
        val r2 = cached(2)

        assertEquals(4, r1)
        assertEquals(4, r2)
        assertEquals(1, callCount, "Функция должна вызываться только один раз для одинаковых аргументов")
    }

    @Test
    fun `должен хранить не более capacity элементов`() {
        var callCount = 0
        val f = { x: Int ->
            callCount++
            x * 10
        }

        val cached = memoizeWith(2, f)

        cached(1) // вычислит и добавит в кэш
        cached(2) // вычислит и добавит в кэш
        cached(3) // вычислит и вытеснит первый (1)
        cached(1) // должен вычислить снова (1 был вытеснен)

        assertEquals(4, callCount, "Функция должна вызваться 4 раза (из-за вытеснения старого элемента)")
    }

    @Test
    fun `должен возвращать правильные результаты`() {
        val cached = memoizeWith(3) { s: String -> s.reversed() }

        assertEquals("cba", cached("abc"))
        assertEquals("321", cached("123"))
    }

    @Test
    fun `должен корректно работать при повторных вызовах с разными данными`() {
        var callCount = 0
        val f = { s: String ->
            callCount++
            s.uppercase()
        }

        val cached = memoizeWith(3, f)

        cached("a")
        cached("b")
        cached("a")
        cached("c")
        cached("d")
        cached("b") // b был вытеснен

        assertEquals(5, callCount, "Некоторые элементы должны быть пересчитаны после вытеснения")
    }

    @Test
    fun `должен корректно обрабатывать одиночный элемент`() {
        var callCount = 0
        val f = { n: Int ->
            callCount++
            n * 2
        }

        val cached = memoizeWith(1, f)

        cached(1)
        cached(1)
        cached(2)
        cached(1)

        assertEquals(3, callCount, "Функция должна вызываться снова при смене и повторном обращении к вытесненному элементу")
    }
}
