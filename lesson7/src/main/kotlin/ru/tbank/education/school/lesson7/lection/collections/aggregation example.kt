package ru.tbank.education.school.lesson7.lection.collections

fun main() {
    val numbers = listOf(6, 42, 10, 4)

    println("Count: ${numbers.count()}")
    println("Max: ${numbers.maxOrNull()}")
    println("Min: ${numbers.minOrNull()}")
    println("Average: ${numbers.average()}")
    println("Sum: ${numbers.sum()}")

    // reduce — свёртка без начального значения
    val simpleSum = numbers.reduce { sum, element -> sum + element }
    println("Сумма с reduce: $simpleSum")

    // fold — свёртка с начальными данными
    val sumDoubled = numbers.fold(0) { sum, element -> sum + element * 2 }
    println("Сумма удвоенных чисел с fold: $sumDoubled")
}