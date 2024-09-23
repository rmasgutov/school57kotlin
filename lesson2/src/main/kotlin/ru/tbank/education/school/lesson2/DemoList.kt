package ru.tbank.education.school.lesson2

import kotlin.random.Random

object DemoList {
    fun createList(): List<Int> {
        var arr  = (1..5).map { Random.nextInt(1, 100) }
        return arr
    }

    fun sumList(arr: List<Int>): Int {
        var sum = 0
        for (i in arr){
            sum = sum + i
        }
        return sum
    }

    fun sumEvenList(arr: List<Int>): Int {
        var sum = 0
        for (i in arr){
            if(i % 2 == 0){
            sum = sum + i
            }
        }
        return sum
    }

    fun multiplyList(arr: List<Int>): List<Int> {
        val ans = arr.map{it * 2}
        return ans
    }

    fun maxElement(arr: List<Int>): Int {
        return arr.max()
    }

    fun sortDesc(arr: List<Int>): List<Int> {
        val ans = arr.sortedDescending()
        return ans
    }
}