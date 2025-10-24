package ru.tbank.education.school.lesson7.practise.task4

fun riskyOperation(): String {
    if (Math.random() < 0.5)
        throw RuntimeException("Что-то сломалось!")
    return "На этот раз повезло, ошибки нет!"
}

fun riskyDivide(a: Int, b: Int): Result<Int> =
    runCatching { a / b }

fun main() {
    try {
        val result = riskyOperation()
        println("Результат: $result")
    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }

    val result = riskyDivide(10, 0)

    result
        .onSuccess { println("Результат: $it") }
        .onFailure { println("Ошибка: ${it.message}") }
}

