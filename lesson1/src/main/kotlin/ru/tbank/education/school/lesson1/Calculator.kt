package ru.tbank.education.school.lesson1
import kotlin.math.sqrt
import kotlin.math.sin
import kotlin.math.cos

fun calculate(a: Double, b: Double, operation: OperationType): Double? {
    return when(operation) {
        OperationType.ADD -> a + b
        OperationType.SUBTRACT -> a - b
        OperationType.MULTIPLY -> a * b
        OperationType.DIVIDE -> if (b == 0.0) null else a / b
        OperationType.SQRT -> sqrt(a)
        OperationType.SIN -> sin(a)
        OperationType.COS -> cos(a)
    }
}

fun detectOperation(op: String): OperationType? {
    when(op) {
        "+" -> return OperationType.ADD
        "-" -> return OperationType.SUBTRACT
        "*" -> return OperationType.MULTIPLY
        "/" -> return OperationType.DIVIDE
        "sin" -> return OperationType.SIN
        "cos" -> return OperationType.COS
        "sqrt" -> return OperationType.SQRT
    }
    return null
}

@Suppress("ReturnCount")
fun String.calculate(): Double? {
    val s = this.split(" ")
    if (s.size < 2 || s.size > 3) { return null }
    if (s.size == 2) {
        val op = detectOperation(s[0]) ?: return s[0].toDouble() + s[1].toDouble()
        val a = s[1].toDoubleOrNull() ?: return null
        return calculate(a, 0.0, op)
    }

    val a = s[0].toDoubleOrNull() ?: return null
    val b = s[2].toDoubleOrNull() ?: return null
    val op = detectOperation(s[1]) ?: return null

    return calculate(a, b, op)
}

fun main() {
    var s = readln()
    s.calculate()?.let { println(it) }
    //println(s.calculate())
}