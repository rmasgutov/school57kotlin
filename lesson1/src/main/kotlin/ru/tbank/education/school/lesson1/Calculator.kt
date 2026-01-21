package ru.tbank.education.school.lesson1


@Suppress("ReturnCount")
fun String.calculate(): Double? {
    TODO()
}


fun calculate(a: Double?, b: Double?, operation: OperationType = OperationType.ADD): Double? {
    val x = a ?: return null
    val y = b ?: return null

    return when (operation) {
        OperationType.ADD -> x + y
        OperationType.SUBTRACT -> x - y
        OperationType.MULTIPLY -> x * y
        OperationType.DIVIDE -> if (y == 0.0) null else x / y
    }
}
