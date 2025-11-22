package ru.tbank.education.school.lesson8.lection.calculator

import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CalculatorTest {

    private lateinit var calc: Calculator

    @BeforeEach
    fun setUp() {
        // Arrange
        calc = Calculator()
    }

    @AfterEach
    fun tearDown() {
        // Здесь можно закрывать ресурсы, очищать состояние
    }

    @Test
    fun `add should sum two integers`() {
        // Act
        val result = calc.add(2, 3)
        // Assert
        assertEquals(5, result)
    }

    @ParameterizedTest(name = "multiply {0} * {1} = {2}")
    @CsvSource(
        "2, 3, 6",
        "-1, 5, -5",
        "0, 10, 0"
    )
    fun `multiply works for various inputs`(a: Int, b: Int, expected: Int) {
        // AAA: действие и проверка
        assertEquals(expected, calc.multiply(a, b))
    }

    @Test
    fun `divide should throw on division by zero`() {
        val ex = assertThrows<IllegalArgumentException> {
            // Act
            calc.divide(10, 0)
        }
        // Assert
        assertEquals("Division by zero", ex.message)
    }

    @Test
    fun `assertAll example`() {
        assertAll(
            { assertEquals(5, calc.add(2, 3)) },
            { assertEquals(-1, calc.subtract(2, 3)) },
            { assertEquals(6, calc.multiply(2, 3)) }
        )
    }
}
