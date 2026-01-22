package ru.tbank.education.school.lesson1

import kotlin.math.*

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(
    a: Double = 0.0,
    b: Double = 0.0,
    operation: OperationType = OperationType.ADD,
    expression: String? = null
): Double? {
    expression?.let {
        return calculateExpression(expression)
    }
    val ans = when (operation) {
        OperationType.ADD -> a + b
        OperationType.SUBTRACT -> a - b
        OperationType.MULTIPLY -> a * b
        OperationType.DIVIDE -> (a / b).takeIf { b != 0.0 }
        OperationType.SIN -> sin(a)
        OperationType.COS -> cos(a)
        OperationType.SQRT -> sqrt(a)
    }
    ans?.let {
        println("Ответ: $it")
    } ?: println("Ошибка: деление на ноль невозможно")

    return ans
}

/**
 * Метод для вычисления выражения, представленного строкой.
 * @return результат вычисления строки или null, если вычисление невозможно
 */
private fun calculateExpression(expression: String): Double? {
    val components = expression.trim().split("\\s+".toRegex())

    if (components.size == 3) {
        val a = components[0].toDoubleOrNull() ?: return null
        val b = components[2].toDoubleOrNull() ?: return null

        val operation = when (components[1]) {
            "+" -> OperationType.ADD
            "-" -> OperationType.SUBTRACT
            "*" -> OperationType.MULTIPLY
            "/" -> OperationType.DIVIDE
            else -> return null
        }

        return calculate(a, b, operation)
    } else if (components.size == 2) {
        val operation = when (components[0]) {
            "sin" -> OperationType.SIN
            "cos" -> OperationType.COS
            "sqrt" -> OperationType.SQRT
            else -> return null
        }

        val a = components[1].toDoubleOrNull() ?: return null

        return calculate(a, operation = operation)
    }
    return null
}

/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): Double? {
    return calculate(expression = this)
}
