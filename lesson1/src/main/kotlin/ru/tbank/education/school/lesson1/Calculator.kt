package ru.tbank.education.school.lesson1

import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.sqrt
/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double = 2.0, b: Double = 2.0, operation: OperationType = OperationType.MULTIPLY): Double? {
        val x = a ?: return null
        val y = b ?: return null
        val ans: Double? = when (operation) {
            OperationType.ADD -> x + y
            OperationType.SUBTRACT -> x - y
            OperationType.MULTIPLY -> x * y
            OperationType.DIVIDE -> if (y != 0.0) x / y else null
            OperationType.SIN -> sin(x)
            OperationType.COS -> cos(x)
            OperationType.SQRT -> if (x >= 0) sqrt(x) else null
            else -> null
        }
        ans?.let {
            println("Ответ: $it")
        } ?: println("Ошибка: нельзя делить на ноль или sqrt из отрицательного числа")
        return ans

}

/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): Double? {
    val data = this.trim().split(" ")
    if (data.size != 3) {
        println("Ошибка: неправильный формат.")
        return null
    }
    val a = data[0].toDoubleOrNull()
    val operation = data[1]
    val b = data[2].toDoubleOrNull()
    if (a == null || b == null) {
        println("Ошибка: число отсутствует")
        return null
    }
    val operationType = when (operation) {
        "+" -> OperationType.ADD
        "-" -> OperationType.SUBTRACT
        "*" -> OperationType.MULTIPLY
        "/" -> OperationType.DIVIDE
        else -> {
            println("Ошибка: неправильная операция")
            return null
        }
    }
    return calculate(a, b, operationType)
}
