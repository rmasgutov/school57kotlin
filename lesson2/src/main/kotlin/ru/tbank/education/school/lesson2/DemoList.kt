package ru.tbank.education.school.lesson2

object DemoList {
    fun createList(): List<Int> {
        var data = listOf(1, 5, 8, 30, 27)
        return data
    }

    fun sumList(data: List<Int>): Int {
        return data.sum()
    }

    fun sumEvenList(data: List<Int>): Int {
        var res = 0
        for (i in 0..data.size) {
            res = res + data[i]
        }
        return res
    }

    fun multiplyList(data: List<Int>): List<Int> {
        return data.map { it * 2 }
    }

    fun maxElement(data: List<Int>): Int {
        return data.max()
    }

    fun sortDesc(data: List<Int>): List<Int> {
        return data.sorted().reversed()
    }
}
