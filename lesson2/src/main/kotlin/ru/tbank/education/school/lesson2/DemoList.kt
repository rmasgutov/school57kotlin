package ru.tbank.education.school.lesson2
import kotlin.random.Random
object DemoList {
    fun createList(): List<Int> {
        val list = mutableListOf<Int>()
        for (i in 1..5){
            list.add(Random.nextInt(0,101))
        }
        return list
    }


    fun sumList(list: List<Int>): Int {
        var sum = 0
        list.forEach {
            sum += it
        }
        return sum
    }

    fun sumEvenList(list: List<Int>): Int {
        var sumEven = 0
        list.forEach {
            if (it%2==0) {
                sumEven += it
            }
        }
        return sumEven
    }

    fun multiplyList(list: MutableList<Int>): List<Int> {
     for (i in list.indices){
         list[i] *= 2
     }
        return list
    }

    fun maxElement(list: List<Int>): Int {
        return list.max()!!
    }

    fun sortDesc(list: List<Int>): List<Int> {
        return list.sortedDescending()
    }
}

