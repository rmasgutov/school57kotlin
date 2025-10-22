package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoList {
    fun createList(): List<Int> {
        return List(5) { Random.nextInt(1, 101) }
    }

    fun sumList(): Int {
        val list = createList()
        println("Список: $list")
        val sum = list.sum()
        println("Сумма элементов: $sum")
        return sum
    }

    fun sumEvenList(): Int {
        val list = createList()
        println("Список: $list")
        val sumEven = list.filter { it % 2 == 0 }.sum()
        println("Сумма чётных элементов: $sumEven")
        return sumEven
    }

    fun multiplyList(): List<Int> {
        val list = createList()
        val multiplied = list.map { it * 2 }
        println("Исходный: $list\nУмноженный: $multiplied")
        return multiplied
    }

    fun maxElement(): Int {
        val list = createList()
        val max = list.maxOrNull() ?: 0
        println("Список: $list\nМаксимум: $max")
        return max
    }

    fun sortDesc(): List<Int> {
        val list = createList()
        val sorted = list.sortedDescending()
        println("Исходный: $list\nПо убыванию: $sorted")
        return sorted
    }
}