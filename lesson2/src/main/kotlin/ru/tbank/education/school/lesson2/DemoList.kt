package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoList {
    fun createList(): List<Int> {
        val list = ArrayList<Int>()
        for (i in 0..5) {
            list.add(Random.nextInt(0, 100))
        }
        return list
    }

    fun sumList(list: List<Int>): Int = list.sum()

    fun sumEvenList(list: List<Int>): Int = list.filter { it % 2 == 0 }.sum()

    fun multiplyList(list: List<Int>): List<Int> = list.map { it * 2 }

    fun maxElement(list: List<Int>): Int = list.max()

    fun sortDesc(list: List<Int>): List<Int> = list.sortedDescending()
}
