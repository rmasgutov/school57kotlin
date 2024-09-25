package ru.tbank.education.school.lesson2
import kotlin.random.Random
object DemoList {
    private val list: List = createList()
    fun createList(): List<Int> {
        return List(5) { Random.nextInt(1, 101) }
    }
    fun sumList(): Int {
        return list().sum()
    }
    fun sumEvenList(): Int {
        return list().filter { it % 2 == 0 }.sum()
    }
    fun multiplyList(): List<Int> {
        return list().map { it * 2 }
    }
    fun maxElement(): Int {
        return list().maxOrNull()
    }
    fun sortDesc(): List<Int> {
        return list().sortedDescending()
    }
}
