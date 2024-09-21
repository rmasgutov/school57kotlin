package ru.tbank.education.school.lesson2
import kotlin.random.Random
object DemoList {
    fun createList(): List<Int> {
        return List(5){Random.nextInt(1, 101)}
    }

    fun sumList(): Int {
        return createList().sum()
    }

    fun sumEvenList(): Int {
        return createList().filter {it % 2 == 0}.sum()
    }

    fun multiplyList(): List<Int> {
        return createList().map{it * 2}
    }

    fun maxElement(): Int {
        return createList().max()
    }

    fun sortDesc(): List<Int> {
        return createList().sortedDescending()
    }
}
