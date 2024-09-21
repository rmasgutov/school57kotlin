package ru.tbank.education.school.lesson2

fun sorting(arr: Array<Int>){
    arr.sort()
}
fun main(){
    val arr = arrayOf(1, 2, 4, 3, 6)
    sorting(arr)
    for(item in arr){
        println(item)
    }
}