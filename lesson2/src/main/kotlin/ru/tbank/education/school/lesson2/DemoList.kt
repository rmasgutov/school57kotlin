package ru.tbank.education.school.lesson2

import kotlin.random.Random
object DemoList {
    fun createList(): List<Int> {
        TODO()
        return List(5) {Random.nextInt(1, 101)}
    }

    fun sumList(): Int {
        TODO()
        return createList().sum()
    }

    fun sumEvenList(): Int {
        TODO()
        val data = createList()
        val evenData = data.filter { it % 2 == 0 }
        return evenData.sum()
    }

    fun multiplyList(): List<Int> {
        TODO()
        return createList().map { it.times(other = 2) }
    }

    fun maxElement(): Int {
        TODO()
        return createList().max()
    }

    fun sortDesc(): List<Int> {
        TODO()
        return createList().sortedDescending()
    }
}