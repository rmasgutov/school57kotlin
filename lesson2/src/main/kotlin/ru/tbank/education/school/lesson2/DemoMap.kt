package ru.tbank.education.school.lesson2
import kotlin.random.Random

object DemoMap {
    private val map: Map<String, Int> = createMap()

    fun createMap(): Map<String, Int> {
        return List(5) {
            generateRandomString(Random.nextInt(5, 11)) to Random.nextInt(1, 101)
        }.toMap()
    }

    fun maxValue(): Int {
        return map.values.maxOrNull() ?: 0
    }

    fun keyForMaxValue(): String {
        return map.maxByOrNull { it.value }?.key ?: ""
    }

    fun sortByValueDesc(): Map<String, Int> {
        return map.toList().sortedByDescending { it.second }.toMap()
    }

    fun filterOddValues(): Map<String, Int> {
        return map.filter { it.value % 2 != 0 }
    }

    private fun generateRandomString(length: Int): String {
        val chars = ('a'..'z')
        return (1..length).map { chars.random() }.joinToString("")
    }
}