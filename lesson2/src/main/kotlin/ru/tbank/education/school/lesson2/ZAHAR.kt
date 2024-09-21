package ru.tbank.education.school.lesson2

fun main() {
    var a = arrayOf(2, 3, 4, 3, 6, 2, 3, 3)
    for (i in a) {
        var count = a.count { it == i }
        if (count > a.size / 2) {
            println(i)
            break
        }
    }
}