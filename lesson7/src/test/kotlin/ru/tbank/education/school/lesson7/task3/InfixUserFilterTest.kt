package ru.tbank.education.school.lesson7.task3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task3.User
import ru.tbank.education.school.lesson7.practise.task3.ageGreaterThan
import ru.tbank.education.school.lesson7.practise.task3.livesIn

class InfixUserFilterTest {

    private val users = listOf(
        User("Anna", 30, "Москва"),
        User("Ivan", 20, "Москва"),
        User("Olga", 27, "Санкт-Петербург"),
        User("Pavel", 40, "Казань")
    )

    @Test
    fun `filters users older than 25`() {
        val result = users.filter { it ageGreaterThan 25 }

        assertEquals(3, result.size)
        assertTrue(result.all { it.age > 25 })
        assertEquals(listOf("Anna", "Olga", "Pavel"), result.map { it.name })
    }

    @Test
    fun `filters users living in Moscow`() {
        val result = users.filter { it livesIn "Москва" }

        assertEquals(2, result.size)
        assertTrue(result.all { it.city == "Москва" })
        assertEquals(listOf("Anna", "Ivan"), result.map { it.name })
    }

    @Test
    fun `returns empty list when no one matches`() {
        val result = users.filter { it livesIn "Новосибирск" }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `works with age boundary`() {
        val users = listOf(
            User("Tim", 25, "Москва"),
            User("Sam", 26, "Москва")
        )

        val result = users.filter { it ageGreaterThan 25 }

        assertEquals(1, result.size)
        assertEquals("Sam", result.first().name)
    }
}
