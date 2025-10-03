package ru.tbank.education.school.lesson1

/**
 * Метод для вычисления простых арифметических операций.
 */
import kotlin.math.*

enum class Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE, SIN, COS, SQRT
}


fun calculate(
    a: Double = 0.0,
    b: Double? = null,
    operation: Operation? = null
): Double? {

    val op = operation ?: Operation.ADD

    return when (op) {
        Operation.ADD -> a + (b ?: 0.0)
        Operation.SUBTRACT -> a - (b ?: 0.0)
        Operation.MULTIPLY -> a * (b ?: 1.0)
        Operation.DIVIDE -> if (b == 0.0) null else a / b
        Operation.SIN -> sin(a)
        Operation.COS -> cos(a)
        Operation.SQRT -> if (a < 0) null else sqrt(a)
    } as Double?
}

fun String.evaluate(): Double? {
    val tokens = this.trim().split(" ")
    val lenght = tokens.size

    return when (lenght) {
        2 -> {
            val func = tokens[0]
            val num = tokens[1]
            val n = num.toDoubleOrNull() ?: return null
            return when (func) {
                "sin" -> calculate(n, operation = Operation.SIN)
                "cos" -> calculate(a = n, operation = Operation.COS)
                "sqrt" -> calculate(a = n, operation = Operation.SQRT)
                else -> null
            }
        }

        3 -> {
            val a = tokens[0].toDoubleOrNull() ?: return null
            val func = tokens[1]
            val b = tokens[2].toDoubleOrNull() ?: return null

            if (a == null || b == null) return null

            val operation = when (func) {
                "+" -> Operation.ADD
                "-" -> Operation.SUBTRACT
                "*" -> Operation.MULTIPLY
                "/" -> Operation.DIVIDE
                else -> null
            }

            if (operation == null) null
            else calculate(a = a, b = b, operation = operation)
        }

        else -> null
    }

}

// Пишет что у b должен быть тип Double (в строке 20) я не понимаю почему
