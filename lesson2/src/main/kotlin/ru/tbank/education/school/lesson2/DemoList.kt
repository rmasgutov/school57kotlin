package ru.tbank.education.school.lesson2
import kotlin.random.Random
object DemoList {
    fun createList(): List<Int> {
        return listOf(Random.nextInt(1,101), Random.nextInt(1,101),Random.nextInt(1,101),Random.nextInt(1,101),Random.nextInt(1,101))
    }

    fun sumList(): Int {
        var u = 0
        for (i in createList()) {
            u = u + i
        }
        return u
    }
        fun sumEvenList(): Int {
            var u = 0
            for (i in createList()) {
                if (i % 2 == 0) {
                    u = u + i
                }
            }
            return u
        }

        fun multiplyList(): List<Int> {
            val result = createList().map { it * 2 }
            return result
        }

        fun maxElement(): Int {
            var maxx = 0
            for (i in createList()) {
                if (i > maxx) {
                    maxx = i
                }
            }
            return maxx
        }

        fun sortDesc(): List<Int> {
            return createList().sorted().reversed()
        }
    }
