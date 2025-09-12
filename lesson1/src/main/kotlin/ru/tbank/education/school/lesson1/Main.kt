package ru.tbank.education.school.lesson1

fun main() {
    var a = 1
    var b = 2
    val min = if (a > b) b else a
    /*println(min)*/

    /*when {

    }*/

    var arr = arrayOf(1, 2, 3, 4)
    println("arr: ${arr.joinToString()}")

    println("arr items:")
    for (item in arr) {
        println(item)
    }

    // require(a > 1)
}