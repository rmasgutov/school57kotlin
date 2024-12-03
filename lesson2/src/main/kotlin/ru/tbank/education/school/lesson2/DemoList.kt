package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoList {
    fun createList(): List<Int> {
        return  listOf(Random.nextInt(1, 100),Random.nextInt(1, 100),Random.nextInt(1, 100),Random.nextInt(1, 100),Random.nextInt(1, 100))
    }

    fun sumList(lis: List<Int>): Int {
        return lis.sum()
    }

    fun sumEvenList(lis: List<Int>): Int {
        return lis.filter { it % 2 == 0 }.sum()
    }

    fun multiplyList(lis: List<Int>): List<Int> {
        return lis.map { it * 2 }
    }

    fun maxElement(lis: List<Int>): Int {
        return lis.max()
    }

    fun sortDesc(lis: List<Int>): List<Int> {
        return lis.sorted().reversed()
    }

}
fun main(){
    var a = DemoList.createList()
    println(a)
    println(DemoList.sumList(a))
    println(DemoList.sumEvenList(a))
    println(DemoList.multiplyList(a))
    println(DemoList.maxElement(a))
    println(DemoList.sortDesc(a))
}