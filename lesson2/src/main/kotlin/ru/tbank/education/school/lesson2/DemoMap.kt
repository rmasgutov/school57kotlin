package ru.tbank.education.school.lesson2

object DemoMap {
    fun createMap(): Map<String, Int> {
        val map = mapOf("aa" to 5, "bb" to 12, "cc" to 11, "dd" to 15,"gg" to 10 )
        return map
    }

    fun maxValue(): Int {
        return createMap().values.max()
    }

    fun keyForMaxValue(): String {
        val t = createMap().values.max()
        val y: String = createMap().entries.find{it.value == t}?.key
        return y
    }

    fun sortByValueDesc(): Map<String, Int> {
        return createMap().entries.sortedByDescending { it.value }.associateBy({it.key}, {it.value})
    }

    fun filterOddValues(): Map<String, Int> {
        return createMap().filterValues { it%2 == 0 }
    }
}
