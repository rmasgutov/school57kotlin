package ru.tbank.education.school.lesson2

object DemoList {
    fun createList(): List<Int> {
        var v = mutableListOf((0..100).random())
        for (i in 1..4) {v.add((0..100).random())}
        return v
    }

    fun sumList(): Int {
        val v = createList()
        return v.sum()
    }

    fun sumEvenList(): Int {
        val v = createList()
        var summ = 0
        for (i in 0..v.size) {
            if (i % 2 == 0) summ += v[i]
        }
        return summ
    }

    fun multiplyList(): List<Int> {
        var v = createList()
        return v.map { it * 2 }
    }

    fun maxElement(): Int {
        val v = createList()
        return v.max()
    }

    fun sortDesc(): List<Int> {
        val v = createList()
        return v.sorted()
    }
}
