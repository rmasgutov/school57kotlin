package ru.tbank.education.school.lesson7.lection

// Функция высшего порядка: принимает другую функцию как аргумент
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}


fun main() {
    // Лямбда для сложения
    val lambdaSum: (Int, Int) -> Int = { a, b -> a + b }

    // Лямбда для умножения
    val lambdaMultiply: (Int, Int) -> Int = { a, b -> a * b }

    // Используем функцию calculate с разными лямбдами
    println(calculate(a = 5, b = 3, operation = lambdaSum))
    println(calculate(a = 5, b = 3, operation = lambdaMultiply))
}
