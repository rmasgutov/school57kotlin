package ru.tbank.education.school.lesson2

fun main() {
    val array = arrayOf(1, 2, 4, 5, 8, 2, 3, 2, 2, 2)
    var count = 0
    var i = 0
    array.sort()
    for(num in array){
        if (i == 0 || num != array[i-1]){
            count = 0
        }
        count = count + 1
        i = i+1
        if (count >= array.size / 2){
            println(num)
            break
        }
    }
}