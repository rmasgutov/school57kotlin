package ru.tbank.education.school.lesson7.lection

fun main() {
    val numbersList = listOf(1, 2, 3, 4, 5)

    val resultList = numbersList
        .map {
            println("map $it")
            it * 2
        }
        .filter {
            println("filter $it")
            it > 5
        }
        .toList()

    println("Результат: $resultList")

    println("------------------------")

    val numbersSequence = sequenceOf(1, 2, 3, 4, 5)

    val resultSequence = numbersSequence
        .map {
            println("map $it")
            it * 2
        }
        .filter {
            println("filter $it")
            it > 5
        }
        .toList()

    println("Результат: $resultSequence")
}
