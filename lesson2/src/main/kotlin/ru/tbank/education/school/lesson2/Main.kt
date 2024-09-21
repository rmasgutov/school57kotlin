package ru.tbank.education.school.lesson2

fun main() {
    val arr = arrayOf(2, 3, 2, 5, 2, 2, 2, 1)
    arr.sort()
    var left = 0
    var right = 1
    while (right < arr.size) {
        while (right < arr.size && arr[right] == arr[right - 1]){
            ++right
        }
        if (right - left > arr.size / 2) {
            println(arr[left])
        }
        left = right
        ++right
    }
}
