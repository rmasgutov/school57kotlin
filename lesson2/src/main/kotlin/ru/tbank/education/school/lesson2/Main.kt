package ru.tbank.education.school.lesson2


fun main(){
    println("DemoList")
    var a = DemoList.createList()
    println(a)
    println(DemoList.sumList(a))
    println(DemoList.sumEvenList(a))
    println(DemoList.multiplyList(a))
    println(DemoList.maxElement(a))
    println(DemoList.sortDesc(a))

    println("DemoMap")
    val b = DemoMap.createMap()
    for ((key, value )in b){
        println("$key : $value")
    }
    println(DemoMap.maxValue(b))
    println(DemoMap.keyForMaxValue(b))
    println(DemoMap.sortByValueDesc(b))
    for ((key, value )in DemoMap.sortByValueDesc(b)){
        println("$key : $value")
    }
    println(DemoMap.filterOddValues(b))
    for ((key, value )in DemoMap.filterOddValues(b)){
        println("$key : $value")
    }
}
