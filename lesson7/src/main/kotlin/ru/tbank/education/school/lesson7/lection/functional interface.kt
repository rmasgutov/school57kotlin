package ru.tbank.education.school.lesson7.lection

fun interface Operation {
    fun apply(a: Int, b: Int): Int
}

fun calculate(a: Int, b: Int, operation: Operation): Int {
    return operation.apply(a, b)
}

fun main() {
    val sumOperation = object : Operation {
        override fun apply(a: Int, b: Int): Int = a + b
    }
    val multiplyOperation = object : Operation {
        override fun apply(a: Int, b: Int): Int = a * b
    }
    
    println(calculate(a = 5, b = 3, operation = sumOperation))       // 8
    println(calculate(a = 5, b = 3, operation = multiplyOperation))  // 15
}
