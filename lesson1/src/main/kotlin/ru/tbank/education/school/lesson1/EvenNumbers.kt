package ru.tbank.education.school.lesson1
fun sumEvenNumbers(numbers: Array<Int>): Int {
    var s=0
    for (f in numbers) {
        if (f%2==0) {
            s=s+f
        }
    }
    return s
}
fun main() {
    val input = readlnOrNull() ?: ""

    val A = input.split(" ")
        .filter { it.isNotBlank() }
        .map { it.toInt() }
        .toTypedArray()
    val result = sumEvenNumbers(A)
    println("Сумма четных чисел: $result")
}
