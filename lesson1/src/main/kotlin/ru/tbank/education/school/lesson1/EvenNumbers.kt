package ru.tbank.education.school.lesson1

/**
 * Сумма четных чисел.
 */
fun sumEvenNumbers(numbers: Array<Int>): Int {
    var sum = 0
    for (i in numbers) {
        if (i % 2 == 0) {
            sum += i
        }
    }
    return sum
}
