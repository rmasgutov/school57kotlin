package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoMap {
    fun createMap(): Map<String, Int> {
        val ans = mutableMapOf<String, Int> ()
        for (num in 1..5) {
            ans[Random.nextInt(10000, 1000000001).toString()] = Random.nextInt(1, 101)
        }
        return ans
    }
    fun maxValue(): Int {
        return createMap().values.max()
    }

    fun keyForMaxValue(): String {
        return createMap().maxBy { it.value }.key
    }

    fun sortByValueDesc(): Map<String, Int> {
        return createMap().toList().sortedByDescending { (_, value) -> value }.toMap()
    }

    fun filterOddValues(): Map<String, Int> {
        return createMap().filter { it.value % 2 == 0 }
    }
    fun printMap(): List<String> {
        val data = createMap()
        return data.map { "${it.key}: ${it.value}" }
    }
}
