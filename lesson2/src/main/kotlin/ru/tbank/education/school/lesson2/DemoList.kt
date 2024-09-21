package ru.tbank.education.school.lesson2

object DemoList {
    fun createList(): List<Int> {
         return listOf(1, 6, 7, 89, 45 ,67 , 9, 11, 34, 53, 22)
    }

    fun sumList(): Int {
        var counter = 0
        for (num in createList()) {
            counter = counter + num
        }
        return counter
    }

    fun sumEvenList(): Int {
        var even_counter = 0
        for (num in createList()) {
            if (num % 2 == 0) {
                even_counter = even_counter + num
            }
        }
        return even_counter
    }

    fun multiplyList(): List<Int> {
        val result = createList().map{it *2}
        return result
    }

    fun maxElement(): Int {
        var maxx = 0
        for (num in createList()) {
            if (num > maxx) {
                maxx = num
            }
        }
        return maxx
    }

    fun sortDesc(): List<Int> {
        return createList().sorted().reversed()
    }
}
