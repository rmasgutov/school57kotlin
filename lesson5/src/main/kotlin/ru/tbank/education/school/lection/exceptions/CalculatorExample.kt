package ru.tbank.education.school.lection.exceptions

fun divide(a: Int, b: Int) = a / b

fun main() {
    val result = divide(10, 0)
    println(result)
}