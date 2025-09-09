package ru.tbank.education.school.lesson1

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double, b: Double, operation: OperationType=OperationType.ADD ): Double? {
    return when(operation){
        OperationType.ADD -> a+b
        OperationType.SUBTRACT -> a-b
        OperationType.MULTIPLY -> a*b
        OperationType.DIVIDE ->{
            if (b == 0.0) null else a/b
        }
    }
}
fun main() {
    val input = readLine() ?: ""
    val result = input.calculate()
    println("Результат: $result")
}
/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): Double? {
    val parts = this.trim().split(" ")
    if (parts.size != 3) return null
    val a = parts[0].toDoubleOrNull() ?: return null
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
