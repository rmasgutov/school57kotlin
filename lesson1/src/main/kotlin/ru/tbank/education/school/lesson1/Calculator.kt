package ru.tbank.education.school.lesson1

import java.math.BigDecimal

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: BigDecimal, b: BigDecimal, operation: OperationType = OperationType.ADD): BigDecimal? {
    val result = when (operation) {
        OperationType.ADD -> a + b
        OperationType.SUBTRACT -> a - b
        OperationType.MULTIPLY -> a * b
        OperationType.DIVIDE -> if (b != BigDecimal.ZERO) a / b else null
        OperationType.POW -> a.pow(b.toInt())
    }

    if (result == null) {
        println("Ошибка: деление на ноль")
    } else {
        println("Результат: $result")
    }

    return result
}

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double, b: Double, operation: OperationType = OperationType.ADD): Double? {
    return calculate(BigDecimal.valueOf(a), BigDecimal.valueOf(b), operation)?.toDouble()
}

/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): BigDecimal? {
    val parts = this.split(" ")
    if (parts.size != 3) {
        return null
    }

    val a = parts[0].toBigDecimalOrNull() ?: run {
        return null
    }

    val b = parts[2].toBigDecimalOrNull() ?: run {
        return null
    }

    val operation = when (parts[1]) {
        "+" -> OperationType.ADD
        "-" -> OperationType.SUBTRACT
        "*" -> OperationType.MULTIPLY
        "/" -> OperationType.DIVIDE
        "^" -> OperationType.POW
        else -> {
            return null
        }
    }

    return calculate(a, b, operation)
}
