package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoMap {

    private val alphabet = ('a'..'z') + ('0'..'9')

    private fun randomKey(): String {
        val len = Random.nextInt(5, 11)
        return buildString {
            repeat(len) { append(alphabet.random()) }
        }
    }

    fun createMap(): Map<String, Int> {
        val map = List(5) { randomKey() to Random.nextInt(1, 101) }.toMap()
        println("Карта: $map")
        return map
    }

    fun maxValue(): Int {
        val map = createMap()
        val max = map.values.maxOrNull() ?: 0
        println("Максимальное значение: $max")
        return max
    }

    fun keyForMaxValue(): String {
        val map = createMap()
        val entry = map.maxByOrNull { it.value }
        println("Ключ с максимальным значением: ${entry?.key}")
        return entry?.key ?: ""
    }

    fun sortByValueDesc(): Map<String, Int> {
        val map = createMap()
        val sorted = map.toList().sortedByDescending { it.second }.toMap()
        println("Отсортировано по убыванию: $sorted")
        return sorted
    }

    fun filterOddValues(): Map<String, Int> {
        val map = createMap()
        val filtered = map.filter { (_, v) -> v % 2 != 0 }
        println("Только нечётные значения: $filtered")
        return filtered
    }
}