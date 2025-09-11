package ru.tbank.education.school.lesson1

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: BigDecimal, b: BigDecimal, operation: OperationType = OperationType.ADD): BigDecimal? =
    when (operation) {
        OperationType.ADD -> a + b
        OperationType.SUBTRACT -> a - b
        OperationType.MULTIPLY -> a * b
        OperationType.DIVIDE -> {
            if (b == BigDecimal(0.0)) {
                null
            } else {
                a.divide(b, 10, RoundingMode.HALF_UP)
            }
        }
    }

fun calculate(a: Double, b: Double, operation: OperationType = OperationType.ADD): Double? =
    calculate(BigDecimal(a), BigDecimal(b), operation)?.toDouble()

/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): Double? {
    val tokens = this.split(" ")
    if (tokens.size != 3) return null
    val a = tokens[0].toDoubleOrNull() ?: return null
    val b = tokens[2].toDoubleOrNull() ?: return null
    val operation = when (tokens[1]) {
        "+" -> OperationType.ADD
        "-" -> OperationType.SUBTRACT
        "*" -> OperationType.MULTIPLY
        "/" -> OperationType.DIVIDE
        else -> return null
    }

    return calculate(a, b, operation)
}

fun main() {
    val res = readlnOrNull()?.calculate()

    if (res == null) {
        println("Ошибка")
    } else {
        println("Результат: $res")
    }
}
