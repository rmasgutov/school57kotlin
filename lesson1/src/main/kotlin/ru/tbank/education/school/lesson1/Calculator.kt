package ru.tbank.education.school.lesson1

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double, b: Double, operation: OperationType = OperationType.ADD): Double? {
    when (operation) {
        OperationType.ADD -> { return a + b }
        OperationType.SUBTRACT -> { return a - b }
        OperationType.MULTIPLY -> { return a * b }
        OperationType.DIVIDE -> (if (b == 0.0) return null else return a / b)
        else -> { return null }
    }

}

/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): Double? {
    var input_string = this
    var type_val = 0
    var str_a = ""
    var str_b = ""
    var oper: OperationType = OperationType.ADD
    for (sym: Char in this) {
        if (sym in "+-*/1234567890."){
            if (sym in "+-*/") {
                if (sym == '+') oper = OperationType.ADD
                else if (sym == '-') oper = OperationType.SUBTRACT
                else if (sym == '*') oper = OperationType.MULTIPLY
                else if (sym == '/') oper = OperationType.DIVIDE
                type_val = 1
            }
            else {
                if (type_val == 0) str_a += sym
                else str_b += sym
            }
        }
    }

    val double_a = str_a.toDoubleOrNull()
    val double_b = str_b.toDoubleOrNull()
    if (type_val != 1 || double_a == null || double_b == null) {
        return null
    } else {
        return calculate(double_a, double_b, oper)
    }
}

fun print_res(value: Double?) {
    value?.let {
        println(value)
    } ?: println("Ошибка: не получилось вычислить значение выражения")
}

fun main() {
    print_res(calculate(2.0, 0.0, OperationType.DIVIDE))
    print_res("2 * 5.5".calculate())
    print_res("2 / 0".calculate())
}
