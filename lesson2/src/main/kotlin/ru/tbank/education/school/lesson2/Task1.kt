package ru.tbank.education.school.lesson2

fun main() {
    val list = arrayOf(2, 3, 4, 3, 6, 2, 3, 3)
    for (position1 in 1..list.lastIndex) {
        for (position2 in position1..list.lastIndex) {
            if (list[position1] == list[position2]) {
                println(list[position1])
                return
            }
        }
    }
}