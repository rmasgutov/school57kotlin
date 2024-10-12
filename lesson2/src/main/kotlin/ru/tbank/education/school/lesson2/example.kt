package ru.tbank.education.school.lesson2

fun main() {
    val arr = arrayOf(1, 1, 1, 2, 2, 3, 4, 2, 2, 2, 2, 2, 2)
    arr.sort()
    var candidate = arr[0]
    var value = 0;
    var value_x = 0;
    for(num in arr){
        if(num == candidate){
            value_x += 1
        }
        else{
            if(value_x > arr.size / 2){
                value = candidate
            }
            candidate = num
            value_x = 0
        }
    }
    println(value)
}