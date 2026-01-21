package ru.tbank.education.school.lesson1

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double, b: Double, operation: OperationType): Double? {
    when (operation) {
        OperationType.ADD -> a + b
        OperationType.SUBTRACT -> a - b
        OperationType.MULTIPLY -> a * b
        OperationType.DIVIDE -> {
            if (b == 0.0) {
                null
            } else {
                a / b
            }
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
    TODO()
}
