package ru.tbank.education.school.lection.exceptions

private var i: Int = 0

fun printNumber(x: Int): Int {
    i = i + 2
    println(i)
    return i + printNumber(i + 2)
}

fun main() {
    printNumber(0)
}