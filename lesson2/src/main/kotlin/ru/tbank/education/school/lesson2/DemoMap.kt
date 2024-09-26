package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoMap {
    fun createMap(): Map<String, Int> {
        val a = listOf("sduyf", "weiuhwefo", "wfkfhi", "efwfluhi", "dfkjhifdk")
        val m = mutableMapOf<String, Int> ()
        for (key in a) {
            val value = Random.nextInt(1, 100)
            m[key] = value
        }
        return m
    }

    fun maxValue(m : Map<String, Int>): Int {
        return m.values.max()
    }

    fun keyForMaxValue(m : Map <String, Int> ): String {
        var maxx = Int.MIN_VALUE
        var s = ""
        for ((key, value) in m) {
            if (value > maxx) {
                maxx = value
                s = key
            }
        }
        return s
    }

    fun sortByValueDesc(m : Map <String, Int>): Map<String, Int> {
        return m.toList().sortedByDescending { it.second }.toMap()
    }

    fun filterOddValues(m : Map <String, Int> ): Map<String, Int> {
        return m.filterValues { it % 2 == 0 }
    }
}
