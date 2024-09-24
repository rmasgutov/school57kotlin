package ru.tbank.education.school.lesson2

fun main() {
    val list = DemoList.createList()
    println("Original list: $list")
    println("Sorted list: ${DemoList.sortDesc(list)}")
    println(DemoList.sumList(list))
    println(DemoList.sumEvenList(list))
    println(DemoList.maxElement(list))
    println(DemoList.multiplyList(list))

    val map = DemoMap.createMap()
    println("Original map: $map")
    for((k, v) in map){
        println("$k: $v")
    }
    println(DemoMap.maxValue(map))
    println(DemoMap.keyForMaxValue(map))
    println(DemoMap.sortByValueDesc(map))
    println(DemoMap.filterOddValues(map))
}
