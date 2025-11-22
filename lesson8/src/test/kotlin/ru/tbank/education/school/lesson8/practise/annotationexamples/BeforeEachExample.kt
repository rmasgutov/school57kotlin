package ru.tbank.education.school.lesson8.practise.annotationexamples

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BeforeEachExample {
    private lateinit var list: MutableList<String>

    @BeforeEach
    fun setUp() {
        list = mutableListOf("apple", "banana")
        println("Список инициализирован: $list")
    }

    @Test
    fun `test list size`() {
        assertEquals(2, list.size)
    }

    @Test
    fun `test list contains apple`() {
        assertTrue(list.contains("apple"))
    }
}
