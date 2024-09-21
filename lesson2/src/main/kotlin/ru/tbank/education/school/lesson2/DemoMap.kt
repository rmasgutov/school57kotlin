package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoMap {
    private var map = mutableMapOf<String, Int>()

    fun createMap(): Map<String, Int> {
        for (i in 1..5) {
            map[Random.nextInt(10000, 99999).toString()] = Random.nextInt(1, 100)
        }
        return map
    }

    fun printMap() {
        for ((key, value) in map) {
            println("$key: $value")
        }
    }

    fun maxValue(): Int {
        return map.maxBy { it.value }.value
    }

    fun keyForMaxValue(): String {
        return map.maxBy { it.value }.key
    }

    fun sortByValueDesc(): Map<String, Int> {
        map = map.toList().sortedBy { it.second }.toMap().toMutableMap()
        return map
    }

    fun filterOddValues(): Map<String, Int> {
        return map.filterValues { it % 2 == 0 }
    }
}
