package ru.tbank.education.school.lesson2

object DemoList {
    fun createList(): List<Int> {
         return listOf(1, 6, 7, 89, 45 ,67 , 9, 11, 34, 53, 22)
    }

    fun sumList(a: List<Int>): Int {
        var counter = 0
        for (num in a) {
            counter += num
        }
        return counter
    }

    fun sumEvenList(a: List<Int>): Int {
        var even_counter = 0


        for (num in a) {
            if (num % 2 == 0) {
                even_counter += num
            }
        }
        return even_counter
    }

    fun multiplyList(a: List<Int>): List<Int> {
        val result = a.map{it *2}
        return result
    }

    fun maxElement(a: List<Int>): Int {
        var maxx = 0
        for (num in a) {
            if (num > maxx) {
                maxx = num
            }
        }
        return maxx
    }

    fun sortDesc(): List<Int> {
        return createList().sortedDescending()
    }
}
