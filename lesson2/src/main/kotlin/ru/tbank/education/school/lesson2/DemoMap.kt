package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoMap {
    fun createMap(): Map<String, Int> {
        fun getRandomString(length: Int): String {
            val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
            return (1..length).map { allowedChars.random() }.joinToString("")
        }

        val map = HashMap<String, Int>()
        for (i in 1..5) {
            map[getRandomString(Random.nextInt(5, 10))] = Random.nextInt(1, 100)
        }

        return map
    }

    fun maxValue(map: Map<String, Int>): Int = map.maxByOrNull { it.value }!!.value

    fun keyForMaxValue(map: Map<String, Int>): String = map.maxByOrNull { it.value }!!.key

    fun sortByValueDesc(map: Map<String, Int>): Map<String, Int> =
        map.toList().sortedByDescending { (_, value) -> value }.toMap(LinkedHashMap())

    fun filterOddValues(map: Map<String, Int>): Map<String, Int> = map.filterValues { it % 2 == 0 }
}
