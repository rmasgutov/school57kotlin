package ru.tbank.education.school.lesson2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class EvenNumbersKtTest {
    @ParameterizedTest
    @MethodSource("simpleTestData")
    fun simpleTest(numbers: List<Int>, expected: Int) {
        assertEquals(expected, sumEvenNumbers(numbers.toList()))
    }

    companion object {
        @JvmStatic
        fun simpleTestData() = listOf(
            Arguments.of(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 30),
            Arguments.of(listOf(1, 3, 5, 7, 9), 0),
        )
    }
}
