package ru.tbank.education.school.lesson2

fun main() {
    val a = arrayOf(2, 3, 4, 3, 6, 2, 3, 3, 3)
    for (i in a){
        val count = a.count {it == i}
        if (count > a.size / 2) {
            print(i)
            break
        }
    }
}