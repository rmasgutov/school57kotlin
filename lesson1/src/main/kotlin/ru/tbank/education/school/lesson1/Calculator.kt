package ru.tbank.education.school.lesson1

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double, b: Double, operation: OperationType): Double? {
    enum class Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE
}
    return when (operation) {
        Operation.ADD -> firstNumber + secondNumber
        Operation.SUBTRACT -> firstNumber - secondNumber
        Operation.MULTIPLY -> firstNumber * secondNumber
        Operation.DIVIDE -> {
            if (secondNumber == 0) {
                null
            } else {
                firstNumber / secondNumber
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
    return try {
        val parts = this.trim().split("\\s+".toRegex())
        if (parts.size != 3) return null
        
        val firstNumber = parts[0].toInt()
        val operator = parts[1]
        val secondNumber = parts[2].toInt()
        
        when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0) firstNumber / secondNumber else null
            else -> null
        }
    } catch (e: Exception) {
        null
    }
}
}
