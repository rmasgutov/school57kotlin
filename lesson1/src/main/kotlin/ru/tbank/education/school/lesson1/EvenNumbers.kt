package ru.tbank.education.school.lesson1

/**
 * Сумма четных чисел.
 */
fun sumEvenNumbers(arr: Array<Int>): Int {
    var sum = 0
    for (num in arr) {
        if (num % 2 == 0) sum = sum + num
    }
    return sum
}

fun main() {
    val list: List<Int> = readln().split(' ').map { it.toInt() }
    println(sumEvenNumbers(list.toTypedArray()))
}
main()
