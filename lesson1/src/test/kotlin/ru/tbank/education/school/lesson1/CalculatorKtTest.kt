package ru.tbank.education.school.lesson1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CalculatorKtTest {

    @ParameterizedTest(name = "{1} {0} {2} = {3}")
    @MethodSource("calculateBaseTestData")
    fun calculateBaseTest(operation: OperationType, operand1: Double, operand2: Double, expected: Double?) {
        // when
        val actual = calculate(operand1, operand2, operation)

        // then
        assertEquals(expected, actual)
    }

    @ParameterizedTest(name = "{0} = {1}")
    @MethodSource("evaluateStringTestData")
    fun evaluateStringTest(expression: String, expected: Double?) {
        // given
        // when
        val actual = expression.calculate()

        // then
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun calculateBaseTestData() = listOf(
            Arguments.of(OperationType.ADD, 10.0, 5.0, 15.0),
            Arguments.of(OperationType.SUBTRACT, 10.0, 5.0, 5.0),
            Arguments.of(OperationType.MULTIPLY, 10.0, 5.0, 50.0),
            Arguments.of(OperationType.DIVIDE, 10.0, 5.0, 2.0),
            Arguments.of(OperationType.DIVIDE, 10.0, 0.0, null),
            Arguments.of(OperationType.DIVIDE, 10.0, 2.0, 5.0),
        )

        @JvmStatic
        fun evaluateStringTestData() = listOf(
            Arguments.of("5 * 2", 10.0),
            Arguments.of("5 - 2", 3.0),
            Arguments.of("5 / 2", 2.5),
            Arguments.of("5 / 0", null),
            Arguments.of("10 + unknown", null),
        )
    }
}
