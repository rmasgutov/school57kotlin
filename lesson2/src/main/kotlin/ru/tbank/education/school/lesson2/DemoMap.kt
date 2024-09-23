package ru.tbank.education.school.lesson2

import kotlin.random.Random

fun getRandomString(length: Int) : String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}

object DemoMap {
    fun createMap(): Map<String, Int> {
        return mutableMapOf(getRandomString(Random.nextInt(5, 10)) to Random.nextInt(), getRandomString(Random.nextInt(1, 100)) to Random.nextInt(), getRandomString(Random.nextInt(5, 100)) to Random.nextInt(), getRandomString(Random.nextInt(5, 100)) to Random.nextInt(), getRandomString(Random.nextInt(5, 100)) to Random.nextInt())
    }
    fun printMap() {
        val a = createMap()
        for (item in a) {
            println("${item.key}: ${item.value}")
        }
    }
    fun maxValue(): Int {
        val a = createMap()
        var b = 0
        for (item in a) {
            if (item.value > b) b = item.value
        }
        return b
    }

    fun keyForMaxValue(): String {
        val a = createMap()
        var b = 0
        var c = "1"
        for (item in a) {
            if (item.value > b) {
                b = item.value
                c = item.key
            }
        }
        return c
    }

    fun sortByValueDesc(): Map<String, Int> {
        var a = createMap().toList()
        return a.sortedBy { it.second }.toMap()
    }

    fun filterOddValues(): Map<String, Int> {
        return createMap().filterValues { it % 2 == 0 }
    }
}
