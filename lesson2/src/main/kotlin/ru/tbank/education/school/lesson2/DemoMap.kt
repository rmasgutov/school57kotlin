package ru.tbank.education.school.lesson2

object DemoMap {
    fun createMap(): Map<String, Int> {
        val data = mapOf("sdjfahgmj" to 3, "dfjhs" to 6, "fgh" to 90, "h" to 35, "hjk" to 56)
        return data
    }

    fun maxValue(data: Map<String, Int>): Int {
        return data.values.max()
    }

    fun keyForMaxValue(data: Map<String, Int>): String {
        val res = data.values.max()
        var ans = ""
        for (key in data.keys) {
            if (data[key] == res) {
                ans = key
                break
            }
        }
        return ans
    }

    fun sortByValueDesc(data: Map<String, Int>): Map<String, Int> {
        return data.toList().sortedByDescending { it.second }.toMap()
    }

    fun filterOddValues(data: Map<String, Int>): Map<String, Int> {
        return data.filterValues { it % 2 != 0 }
    }
}
