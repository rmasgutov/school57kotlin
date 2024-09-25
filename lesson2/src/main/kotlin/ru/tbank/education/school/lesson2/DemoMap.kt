package ru.tbank.education.school.lesson2

import kotlin.random.Random

fun generateRandomString(minLength: Int, maxLength: Int): String {
    val allowedChars = ('a'..'z') + ('A'..'Z')
    return (1..Random.nextInt(minLength, maxLength + 1)).map { allowedChars.random() }.joinToString("")
}

object DemoMap {
    fun createMap(): Map<String, Int> {
        return mapOf(
            generateRandomString(5, 10) to Random.nextInt(1, 101),
            generateRandomString(5, 10) to Random.nextInt(1, 101),
            generateRandomString(5, 10) to Random.nextInt(1, 101),
            generateRandomString(5, 10) to Random.nextInt(1, 101),
            generateRandomString(5, 10) to Random.nextInt(1, 101)
        )
    }

    fun printMap(): List<String> {
        val data = createMap()
        return data.map { "${it.key}: ${it.value}" }
    }

    fun maxValue(): Int {
        return createMap().values.max()
    }

    fun keyForMaxValue(): String? {
        val data = createMap()
        val maxEl = data.values.max()
        return data.entries.find { it.value == maxEl }?.key
    }

    fun sortByValueDesc(): Map<String, Int> {
        return createMap().entries.sortedByDescending { it.value }.associateBy({it.key}, {it.value})
    }

    fun filterOddValues(): Map<String, Int> {
        val data = createMap()
        return data.filterValues { it % 2 == 0 }
    }
}
