package ru.tbank.education.school.lesson2

fun main(){
    val data = arrayOf(2, 3, 4, 3, 6, 2, 3, 3, 3)
    for (i in data){
        val count = data.count {it == i}
        if (count > data.size / 2) {
            println(i)
            break
        }
    }


}