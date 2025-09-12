package ru.tbank.education.school.lesson1

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double, b: Double, operation: OperationType = OperationType.ADD): Double? {
    when (operation) {
        OperationType.ADD -> return a + b
        OperationType.SUBTRACT -> return a - b
        OperationType.MULTIPLY -> return a * b
        OperationType.DIVIDE -> {
            if (b == 0.0) return null
            else return a / b
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
    this.let {
        var res = 0.0
        val a = this.split(" ")[0].toDouble() ?: 0.0
        val b = this.split(" ")[1] ?: "+"
        val c: Double = this.split(" ")[2].toDouble() ?: 0.0
        when (b) {
            "+" -> return a + c
            "-" -> return a - c
            "*" -> return a * c
            "/" -> {
                if (c == 0.0) return null
                else return a / c
            }
        }
    }
    TODO()
}
