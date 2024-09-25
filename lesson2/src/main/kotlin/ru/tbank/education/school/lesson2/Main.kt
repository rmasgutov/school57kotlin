package ru.tbank.education.school.lesson2

fun main() {
    val list = DemoList.createList()
    println("Original list: $list")
    println(DemoList.sumList(list))
    println(DemoList.sumEvenList(list))
    println(DemoList.multiplyList(list))
    println(DemoList.maxElement(list))
    println(DemoList.sortDesc(list))

    val map = DemoMap.createMap()
    println("Original map: $map")
    for ((key, value) in map) {
        println("$key: $value")
    }
    println(DemoMap.maxValue(map))
    println(DemoMap.keyForMaxValue(map))
    println(DemoMap.sortByValueDesc(map))
    println(DemoMap.filterOddValues(map))
}
