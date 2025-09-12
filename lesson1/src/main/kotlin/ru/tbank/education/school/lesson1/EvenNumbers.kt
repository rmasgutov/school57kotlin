//package ru.tbank.education.school.lesson1

/**
 * Сумма четных чисел.
 */
fun sumEvenNumbers(numbers: Array<Int>): Int {
    var sum = 0
    for (item in numbers) {
        if (item % 2 == 0) sum += item
    }
    return sum
}

fun main() {
    println(sumEvenNumbers(arrayOf(1, 2, 3, 4)))
}
