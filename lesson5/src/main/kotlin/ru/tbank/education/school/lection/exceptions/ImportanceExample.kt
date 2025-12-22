package ru.tbank.education.school.lection.exceptions

fun main() {
    val myPrettyNumberStr = "1"
    println(myPrettyNumberStr.toInt())

    val myIncorrectNumberStr = "a"
    println(myIncorrectNumberStr.toInt())

    println("Очень важная строка кода, которая должна запуститься")
}