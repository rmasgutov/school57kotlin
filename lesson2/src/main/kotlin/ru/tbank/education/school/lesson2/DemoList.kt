package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoList {
    private var list = mutableListOf<Int>()

    fun createList(): List<Int> {
        for (i in 0..5) {
            this.list.add(Random.nextInt(1, 100))
        }
        return list
    }

    fun sumList(): Int {
        return list.sum()
    }

    fun sumEvenList(): Int {
        return list.filter { it % 2 == 0 }.sum()
    }

    fun multiplyList(): List<Int> {
        this.list = (list.map { it * 2 }).toMutableList()
        return list
    }

    fun maxElement(): Int {
        return list.maxOrNull() ?: 0
    }

    fun sortDesc(): List<Int> {
        list = list.sortedDescending().toMutableList()
        return list
    }
}
