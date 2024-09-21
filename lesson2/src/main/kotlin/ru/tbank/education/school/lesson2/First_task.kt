package ru.tbank.education.school.lesson2

fun main() {
    val arr = arrayOf(2, 3, 4, 3, 6, 2, 3, 3)
    for (el in arr) {
        var cnt = 0
        for (num in arr) {
            if (num == el) cnt += 1
        }
        if (cnt >= arr.size / 2) {
            println(el)
            break
        }
    }
}