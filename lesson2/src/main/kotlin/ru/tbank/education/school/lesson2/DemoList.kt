package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoList {
    fun createList(): List<Int> {
        val randomList = List(5) { Random.nextInt(1, 100) }
        return randomList
    }

    fun sumList(a: List<Int>): Int {
        var sum = 0
        for (i in a) {
            sum += i
        }
        return sum
    }

    fun sumEvenList(a: List<Int>): Int {
        var sum = 0
        val n = a.size
        for (i in 1..n) {
            if (i % 2 == 0) sum += a[i - 1]
        }
        return sum
    }

    fun multiplyList(a: List<Int>): List<Int> {
        a.map { it * 2 }
        return a
    }

    fun maxElement(a: List<Int>): Int {
        var maxx = 0
        for (i in a) {
            if (i > maxx) maxx = i
        }
        return maxx
    }

    fun sortDesc(a: List<Int>): List<Int> {
        return a.sortedDescending()
    }
}