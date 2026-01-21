package ru.tbank.education.school.lesson1

/**
 * Сумма четных чисел.
 */
fun sumEvenNumbers(numbers: Array<Int>): Int {
    var total = 0
    for (number in numbers) {
        if (number % 2 == 0) total += number
    }
    return total
}

fun main() {
    println(sumEvenNumbers(arrayOf(1, 2, 3, 4, 5)))
}