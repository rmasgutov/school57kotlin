package ru.tbank.education.school.lesson2

fun main() {
    var v = arrayOf(2, 3, 4, 3, 6, 2, 3 ,3)
    var n = v.size / 2
    for (i in v) {
        var cnt = 0


        for (j in v) {
            if (i == j) ++cnt
        }
        if (cnt >= n) {
            println(i)
            break
        }
    }
}
