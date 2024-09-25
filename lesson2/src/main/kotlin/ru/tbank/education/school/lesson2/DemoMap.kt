package ru.tbank.education.school.lesson2
import kotlin.random.Random

object DemoMap {
    fun createMap(): Map<String, Int> {
        return mapOf(Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100))
    }

    fun allVelue() {
        var a = createMap()
        for (item in a)
            println("${item.key}: ${item.value}")

    }

    fun maxValue(): Int {
        return createMap().values.max()
    }

    fun keyForMaxValue(): String {
        return createMap().maxByOrNull { it.value }!!.key
    }

    fun sortByValueDesc(): Map<String, Int> {
        return createMap().toList().sortedByDescending { it.second }.toMap()
    }

    fun filterOddValues(): Map<String, Int> {
        return createMap().filterValues { it % 2 == 1 }
    }
}
