package ru.tbank.education.school.lesson2

fun main() {
    val list = DemoList.createList()
    println("Original list: $list")
    println()
    println("Sum: ${DemoList.sumList(list)}")
    println("Even sum: ${DemoList.sumEvenList(list)}")
    println("Max element: ${DemoList.maxElement(list)}")
    println()
    println("Multiplied list (by 2): ${DemoList.multiplyList(list)}")
    println("Sorted list (DESC): ${DemoList.sortDesc(list)}")
    println("\n\n")

    val map = DemoMap.createMap()
    println("Original map: $map")
    println("Max value: ${DemoMap.maxValue(map)}")
    println("Key for max value: ${DemoMap.keyForMaxValue(map)}")
    println()
    println("Sorted map (LinkedHashMap, DESC): ${DemoMap.sortByValueDesc(map)}")
    println("Map without odd values: ${DemoMap.filterOddValues(map)}")
}
