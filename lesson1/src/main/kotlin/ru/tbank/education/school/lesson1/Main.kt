package ru.tbank.education.school.lesson1

fun main() {
    val numbers = 1..100
    for (i in numbers) {
        if (i % 13 == 0) {
            throw IllegalArgumentException("Число $i делится на 13")
        }
    }
}
