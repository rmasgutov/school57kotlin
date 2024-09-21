package ru.tbank.education.school.lesson2

object DemoMap {
    fun createMap(): Map<String, Int> {
        var data = mapOf("sdjfahgmj" to 3, "dfjhs" to 6, "fgh" to 90, "h" to 35, "hjk" to 56)
        return data
    }

    fun maxValue(data: Map<String, Int>): Int {
        return data.values.max()
    }

    fun keyForMaxValue(data: Map<String, Int>): String {
        var res = data.values.max()
        for (i in data.keys) {
            if (data[i] == res) {
                return i
            }
        }
    }

    fun sortByValueDesc(data: Map<String, Int>): Map<String, Int> {
        return data.toSortedMap()
    }

    fun filterOddValues(data: Map<String, Int>): Map<String, Int> {

    }
}
