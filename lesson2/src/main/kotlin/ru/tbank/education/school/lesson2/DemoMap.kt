package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoMap {

    fun createMap(): Map<String, Int> {
        val result = mutableMapOf<String, Int>()

        for (i in 1..5) {
            val length = Random.nextInt(5, 11)
            result[randomString(length)] = Random.nextInt(1, 101)
        }

        return result
    }

    fun maxValue(): Int {
        return createMap().values.maxOrNull() ?: 0
    }

    fun keyForMaxValue(): String {
        return createMap().maxByOrNull { it.value }!!.key
    }

    fun sortByValueDesc(): Map<String, Int> {
        return createMap().toList().sortedByDescending { (_, value) -> value }.toMap()
    }

    fun filterOddValues(): Map<String, Int> {
        return createMap().filter { it.value % 2 != 0 }
    }

    // source: https://stackoverflow.com/questions/46943860/idiomatic-way-to-generate-a-random-alphanumeric-string-in-kotlin
    private fun randomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

}
