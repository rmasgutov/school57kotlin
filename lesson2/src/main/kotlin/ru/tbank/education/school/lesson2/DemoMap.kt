package ru.tbank.education.school.lesson2

object DemoMap {
    private var map = mutableMapOf<String, Int>()
    fun createMap(): Map<String, Int> {
        return mapOf("a" to 1, "b" to 6, "c" to 7, "d" to 89, "e" to 45, "f" to 67, "g" to 9, "k" to 11, "l" to 34, "m" to 53, "n" to 22)
    }

    fun maxValue(b: Map<String, Int>): Int {
        var maxx = 0
        for ((key, value) in b) {
            if (value > maxx) {
                maxx = value
            }
        }
        return maxx
    }

    fun keyForMaxValue(b: Map<String, Int>): String {
        var maxx = 0
        var s = ""


        for ((key, value) in b) {
            if (value > maxx) {
                maxx = value
                s = key

            }
        }
        return s
    }

    fun sortByValueDesc(b: Map<String, Int>): Map<String, Int> {
        return b.toList().sortedByDescending { it.second }.toMap()
    }

    fun filterOddValues(b: Map<String, Int>): Map<String, Int> {
        return b.filterValues {it % 2 == 0}
    }
}
