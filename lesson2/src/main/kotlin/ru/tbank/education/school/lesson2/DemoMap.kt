package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoMap {
    fun createMap(): Map<String, Int> {
        return mapOf(
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100)
        )
    }


    fun printMap(): List<String> {
        var ans: MutableList<String> = mutableListOf()
        val local = createMap()
        for (el in local.keys) {
            val loc = local[el]
            ans.add("$el: $loc")
        }
        return ans
    }

    fun maxValue(): Int {
        return createMap().values.maxOrNull() ?: 0
    }

    fun keyForMaxValue(): String {
        return createMap().maxByOrNull { it.value }?.key ?: ""
    }

    fun sortByValueDesc(): Map<String, Int> {
        return createMap().toList().sortedByDescending { (_, value) -> value }.toMap()
    }

    fun filterOddValues(): Map<String, Int> {
        return createMap().filter { it.value % 2 == 1 }
    }
}
