package ru.tbank.education.school.lesson1

/**
 * Сумма четных чисел.
 */ё
fun sumEvenNumbers(numbers: Array<Int>): Int {
    var total = 0
    for (number in numbers) {
        if (number % 2 == 0) {
            total += number
        }
    }
    return total
}


fun main() {
    val numbers = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    println(sumEvenNumbers(numbers))
}