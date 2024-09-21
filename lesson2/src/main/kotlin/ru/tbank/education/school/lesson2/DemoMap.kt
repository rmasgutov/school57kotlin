package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoMap {
    fun createMap(): Map<String, Int> {
        val a = listOf("sduyf", "weiuhwefo", "wfkfhi", "efwfluhi", "dfkjhifdk")
        val m = mutableMapOf<String, Int> ()
        for (i in 1..5) {
            val x = Random.nextInt(1, 100)
            val y = Random.nextInt(0, 4)
            m[a[y]] = x
        }
        return m
    }

    fun maxValue(m : Map<String, Int>): Int {
        var maxx = 0
        for ((key, value) in m) {
            if (value > maxx) maxx = value
        }
        return maxx
    }

    fun keyForMaxValue(): String {
        var maxx = 0
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
        m.filterValues { it % 2 == 1 }
    }
}
