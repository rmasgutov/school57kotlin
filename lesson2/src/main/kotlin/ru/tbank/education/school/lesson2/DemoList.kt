package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoList {
    fun createList(): List<Int> {
        return listOf(Random.nextInt(1, 101), Random.nextInt(1, 101), Random.nextInt(1, 101), Random.nextInt(1, 101), Random.nextInt(1, 101))
    }

    fun sumList(): Int {
        return createList().sum()
    }

    fun sumEvenList(): Int {
        val a = createList()
        var sum = 0
        for (item in a) {
            if (item % 2 == 0) {
                sum += item
            }
        }
        return sum
    }

    fun multiplyList(): List<Int> {
        return createList().map { it * 3 }
    }

    fun maxElement(): Int {
        return createList().max()
    }

    fun sortDesc(): List<Int> {
        return createList().sortedDescending()
    }
}
