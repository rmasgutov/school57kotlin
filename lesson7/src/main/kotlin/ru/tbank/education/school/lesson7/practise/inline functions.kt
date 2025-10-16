package ru.tbank.education.school.lesson7.practise

inline fun runTwice(action: () -> Unit) {
    action()
    action()
}

fun main() {
    runTwice { println("Hello") }
}