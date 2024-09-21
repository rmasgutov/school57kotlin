package ru.tbank.education.school.lesson2

fun main() {
    var data = arrayOf(2, 3, 4, 3, 6, 2, 3, 3)
    for (i in data) {
        if (data.count {it == i} > 3){
            println(i)
            break
        }
    }
}