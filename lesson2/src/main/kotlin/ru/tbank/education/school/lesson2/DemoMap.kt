package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoMap {
    fun createMap(): Map<String, Int> {
        val arr = arrayOf("dfbg", "drg", "ethnmghh", "fehtjty", "etre")
        val m = mutableMapOf<String, Int>()
        for (i in arr){
            m.put(i, Random.nextInt(1, 100))
        }
        return m
    }

    fun maxValue(m: Map<String, Int>): Int {
        return m.maxBy { it.value }.value
    }

    fun keyForMaxValue(m: Map<String, Int>): String {
        return m.maxBy { it.value }.key
    }

    fun sortByValueDesc(m: Map<String, Int>): Map<String, Int> {
        val ans = m.toList().sortedByDescending { (k, v) -> v}.toMap()
        return ans
    }

    fun filterOddValues(m: Map<String, Int>): Map<String, Int> {
        val arr = m.toList().filter { (k, v) -> v%2 != 0 }.toMap()
        return arr
    }
}
