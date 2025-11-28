package ru.tbank.education.school.lesson2

fun main() {
    var list = DemoList.createList()
    println("Original list: $list")
    println(DemoList.sumList(list))
    println(DemoList.sumEvenList(list))
    println(DemoList.multiplyList(list as MutableList<Int>))
    println(DemoList.maxElement(list))
    println(DemoList.sortDesc(list))
    println()

    val map: Map<String, Int> = DemoMap.createMap()
    for ((key, value) in map) {
        println("$key -> $value")
    }
    println("Maximum value: ${DemoMap.maxValue(map)}")
    val maxVal = DemoMap.maxValue(map)
    if (maxVal != null) {
        println("Key for the maximum value: ${DemoMap.keyForMaxValue(maxVal, map.toMutableMap())}")
    }
    println("Sorted in descending order:")
    val sortedList = map.entries.sortedByDescending { it.value }
    for ((key, value) in sortedList) {
        println("$key -> $value")
    }
    val filteredMap = DemoMap.filterOddValues(map.toMutableMap())
    println("Odd values:")
    for ((key, value) in filteredMap) {
        println("$key -> $value")
    }
}
