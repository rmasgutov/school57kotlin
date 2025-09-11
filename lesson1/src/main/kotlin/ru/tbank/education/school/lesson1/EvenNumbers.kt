package ru.tbank.education.school.lesson1

fun sumEvenNumbers(mas: Array<Int>): Int {
    var ans = 0
    for (el in mas) {
        if (el % 2 == 0) {
            ans += el
        }
    }
    return ans
}

fun main() {
    println("Input something:")
    val mass = readln()
    val mas = mass.split(" ").map { it.toInt() }.toTypedArray()
    println(sumEvenNumbers(mas))
}
