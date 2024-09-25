package ru.tbank.education.school.lesson2
import kotlin.random.Random

object DemoMap {
    fun createMap(): Map<String, Int> {
        return mapOf(Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100),
            Random.nextInt(10000, 1000000000).toString() to Random.nextInt(1, 100))
    }

    fun maxValue(map:Map<String, Int>): Int {
        return map.values.max()
    }

    fun keyForMaxValue(map:Map<String, Int>): String {
        val m = maxValue(map)
        for (i in map.keys){
            if (map[i] == m) return i
        }
        return ""
    }

    fun sortByValueDesc(map:Map<String, Int>): Map<String, Int> {

        return map.toList().sortedByDescending { (_, value) -> value }.toMap()
    }

    fun filterOddValues(map:Map<String, Int>): Map<String, Int> {
        return map.filter { (_, value) -> value % 2 == 0 }
    }
}
fun main() {
    val a = DemoMap.createMap()
    for ((key, value )in a){
        println("$key : $value")
    }
    println(DemoMap.maxValue(a))
    println(DemoMap.keyForMaxValue(a))
    println(DemoMap.sortByValueDesc(a))
    for ((key, value )in DemoMap.sortByValueDesc(a)){
        println("$key : $value")
    }
    println(DemoMap.filterOddValues(a))
    for ((key, value )in DemoMap.filterOddValues(a)){
        println("$key : $value")
    }
}