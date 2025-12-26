package ru.tbank.education.school.lesson1

fun sumEvenNumbers(numbers: Array<Int>): Int {

    var sum = 0
    for (item in numbers) if (item % 2 == 0) sum = item + sum
    return sum
}
fun main() {
    val arr = arrayOf(1, 2, 34, 543)
    println(sumEvenNumbers(arr))
}
