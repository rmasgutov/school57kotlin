package ru.tbank.education.school.lesson1

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double, b: Double, operation: OperationType = OperationType.ADD): Double? {
//    val result: Double? = null
    return when (operation) {
        OperationType.ADD -> a + b
        OperationType.SUBTRACT -> a - b
        OperationType.MULTIPLY -> a * b
        OperationType.DIVIDE -> if (b != 0.0) a / b else null
    }
//    return result
}

/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): Double? {
    val parts = this.split(" ")
    if (parts.size != 3) return null
    val a = parts[0].toDoubleOrNull() ?: return null
//    val operation = parts[1]
    val b = parts[2].toDoubleOrNull() ?: return null
    val operation = when (parts[1]) {
        "+" -> OperationType.ADD
        "-" -> OperationType.SUBTRACT
        "*" -> OperationType.MULTIPLY
        "/" -> OperationType.DIVIDE
        else -> return null
    }
    return calculate(a, b, operation)
}


fun main() {
    // Example basic function
    calculate(1.0, 2.0, OperationType.ADD).let { println(it ?: "ERROR: null") }
    calculate(1.0, 2.0, OperationType.SUBTRACT).let { println(it ?: "ERROR: null") }
    calculate(1.0, 2.0, OperationType.MULTIPLY).let { println(it ?: "ERROR: null") }
    calculate(1.0, 2.0, OperationType.DIVIDE).let { println(it ?: "ERROR: null") }
    calculate(1.0, 0.0, OperationType.DIVIDE).let { println(it ?: "ERROR: null") }
    calculate(1.0, 2.0).let { println(it ?: "ERROR: null") }

    // Example string function
    "1 + 2".calculate().let { println(it ?: "ERROR: null") }
    "1 - 2".calculate().let { println(it ?: "ERROR: null") }
    "1 * 2".calculate().let { println(it ?: "ERROR: null") }
    "1 / 2".calculate().let { println(it ?: "ERROR: null") }
    "1 / 0".calculate().let { println(it ?: "ERROR: null") }
}