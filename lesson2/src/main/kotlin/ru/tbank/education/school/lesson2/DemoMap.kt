package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoMap {
    fun createMap(): Map<String, Int> {
        var map = mutableMapOf<String, Int>()
        for (i in 5 downTo 0) {
            var value = Random.nextInt(1, 100)
            var len = Random.nextInt(5, 10)
            map.put(generateRandomString(len), value)

        }
        return map
    }

    private fun generateRandomString(length: Int): String {
        val chars = ('a'..'z') + ('A'..'Z')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }

    fun maxValue(map: Map<String, Int>): Int? {
        return map.values.maxOrNull()
    }

    fun keyForMaxValue(maxValue: Int, map: MutableMap<String, Int>): String {
        val keysList = map.keys.toList()
        val index = map.values.indexOf(maxValue)
        return if (index != -1) keysList[index] else ""
    }

    fun filterOddValues(map: MutableMap<String, Int>): Map<String, Int> {
        val filteredMap = map.filter { it.value % 2 != 0 }
        return filteredMap
        }
    }




