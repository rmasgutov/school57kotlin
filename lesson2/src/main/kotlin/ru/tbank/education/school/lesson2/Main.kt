package ru.tbank.education.school.lesson2

fun main() {
    val list = DemoList.createList()
    println("Original list: $list")
    println("Sum: ${DemoList.sumList(list)}")
    println("Even sum: ${DemoList.sumEvenList(list)}")
    println("Max element: ${DemoList.maxElement(list)}")
    println()
    println("Multiplied list: ${DemoList.multiplyList(list)}")
    println("Sorted list (DESC): ${DemoList.sortDesc(list)}")

//    val map = DemoMap.createMap()
//    println("Original map: $map")
//    TODO()
}
