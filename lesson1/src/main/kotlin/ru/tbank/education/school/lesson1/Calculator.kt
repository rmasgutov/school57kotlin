package ru.tbank.education.school.lesson1

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double, b: Double, operation: OperationType): Double? {
    val ans = when (operation) {
        OperationType.ADD -> a + b
        OperationType.SUBTRACT -> a - b
        OperationType.MULTIPLY -> a * b
        OperationType.DIVIDE -> if (b == 0.0) throw Exception("Деление на ноль") else a / b
    }
    println(ans)
    return ans
}

/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): Double? {
    val nums = split(" ")
    if (nums.size != 3) return null
    val a = nums[0].toBigDecimal()
    val b = nums[2].toBigDecimal()
    val operation = when(nums[1]) {
        "+" -> OperationType.ADD
        "-" -> OperationType.SUBTRACT
        "*" -> OperationType.MULTIPLY
        "/" -> OperationType.DIVIDE
        else -> null
    }
    return ru.tbank.education.school.lesson1.calculate(a, b)
}
