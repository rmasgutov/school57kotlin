package ru.tbank.education.school.lesson7.task3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task3.add
import ru.tbank.education.school.lesson7.practise.task3.divide
import ru.tbank.education.school.lesson7.practise.task3.multiply
import ru.tbank.education.school.lesson7.practise.task3.subtract

class InfixArithmeticTest {

    @Test
    fun `evaluates chained arithmetic operations`() {
        val result = 5 add 3 multiply 2 subtract 4
        // (((5 add 3) multiply 2) subtract 4) = (8 * 2) - 4 = 12
        assertEquals(12, result)
    }

    @Test
    fun `supports division`() {
        val result = 20 divide 5 add 2
        // (20 / 5) + 2 = 4 + 2 = 6
        assertEquals(6, result)
    }

    @Test
    fun `works with negative numbers`() {
        val result = 10 subtract 20 add 5
        // (10 - 20) + 5 = -10 + 5 = -5
        assertEquals(-5, result)
    }

    @Test
    fun `multiply and divide chaining`() {
        val result = 3 multiply 4 divide 2
        // (3 * 4) / 2 = 12 / 2 = 6
        assertEquals(6, result)
    }

    @Test
    fun `chained long expression`() {
        val result = 2 add 3 multiply 4 subtract 1 divide 2
        // ((((2 + 3) * 4) - 1) / 2) = (5 * 4 - 1) / 2 = (20 - 1) / 2 = 9
        assertEquals(9, result)
    }
}
