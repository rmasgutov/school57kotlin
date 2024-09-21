package ru.tbank.education.school.lesson1

enum class OperationType {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
}

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(
    a: Double,
    b: Double,
    operation: OperationType,
): Double? {
    when (operation) {
        OperationType.ADD -> return a + b
        OperationType.SUBTRACT -> return a - b
        OperationType.MULTIPLY -> return a * b
        OperationType.DIVIDE -> {
            if (b == 0.0) {
                throw RuntimeException("Division by zero")
            }
            return a / b
        }
        else -> {
            return null
        }
    }
}

/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): Double? {
    val expression = split(" ")
    var a = expression[0].toDouble()
    var b = expression[2].toDouble()
    return when (expression[1]) {
        "+" -> calculate(a, b, OperationType.ADD)
        "-" -> calculate(a, b, OperationType.SUBTRACT)
        "*" -> calculate(a, b, OperationType.MULTIPLY)
        "/" -> calculate(a, b, OperationType.DIVIDE)
        else -> throw RuntimeException("unknown operation: ${expression[1]}")
    }
}

fun main(args: Array<String>) {
    println("1 % 10".calculate())
    //test
}
