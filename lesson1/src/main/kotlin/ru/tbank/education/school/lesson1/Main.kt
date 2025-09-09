package ru.tbank.education.school.lesson1

import ru.tbank.education.school.lesson1.sumEvenNumbers
import ru.tbank.education.school.lesson1.calculate  // a, b, operation
import ru.tbank.education.school.lesson1.calculate  // string

fun main() {
    // Example EvenNumber

    sumEvenNumbers(arrayOf(1, 2, 4, 5))

    // Example Calculator

    // Example basic function
    calculate(1.0, 2.0, OperationType.ADD).let { println(it ?: "ERROR: null") }
    calculate(1.0, 2.0, OperationType.SUBTRACT).let { println(it ?: "ERROR: null") }
    calculate(1.0, 2.0, OperationType.MULTIPLY).let { println(it ?: "ERROR: null") }
    calculate(1.0, 2.0, OperationType.DIVIDE).let { println(it ?: "ERROR: null") }
    calculate(1.0, 0.0, OperationType.DIVIDE).let { println(it ?: "ERROR: null") }
    calculate(1.0, 2.0).let { println(it ?: "ERROR: null") }

    // Example string function
    "1 + 2".calculate().let { println(it ?: "ERROR: null") }
    "1 - 2".calculate().let { println(it ?: "ERROR: null") }
    "1 * 2".calculate().let { println(it ?: "ERROR: null") }
    "1 / 2".calculate().let { println(it ?: "ERROR: null") }
    "1 / 0".calculate().let { println(it ?: "ERROR: null") }
}