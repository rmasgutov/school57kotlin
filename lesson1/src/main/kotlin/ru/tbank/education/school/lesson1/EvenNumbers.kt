package ru.tbank.education.school.lesson1

/**
 * Сумма четных чисел.
 */
fun sumEvenNumbers(numbers: Array<Int>): Int {
    var q = 0
    for (i in numbers) {
        if (i % 2 == 0) {
            q = q + i
        }
    }
    return q
}
