package ru.tbank.education.school.lesson8.lection.nullsafety

fun main() {
    val unknown: Any = "Rotterdam"
    val safeCastLen = (unknown as? String)?.length ?: 0
    println("safeCastLen=$safeCastLen")

    // Плохой пример с as (небезопасное приведение) может кинуть ClassCastException:
    try {
        val badCastLen = (123 as String).length // ClassCastException
        println("badCastLen=$badCastLen")
    } catch (e: ClassCastException) {
        println("badCastLen crashed with CCE: ${e.message}")
    }
}
