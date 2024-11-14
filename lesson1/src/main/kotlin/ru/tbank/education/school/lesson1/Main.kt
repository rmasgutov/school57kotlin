package ru.tbank.education.school.lesson1

fun main() {
    val a = arrayOf(2, 3, 4, 3, 6, 2, 3, 3, 3, 3, 3, 3)
    val b=1
    b+=1
    println(b)
    var candidate = a[0]
    var count = 0

    for (num in a) {
        if (count == 0) {
            candidate = num
        }
        count += if (num == candidate) 1 else -1
    }
    count = 0
    for (num in a) {
        if (num == candidate) {
            count++
        }
    }
    if (count > a.size / 2) {
        println(candidate)
    }
}