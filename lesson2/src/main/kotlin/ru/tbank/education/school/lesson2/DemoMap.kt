package ru.tbank.education.school.lesson2
import kotlin.random.Random

object DemoMap {

    fun createMap(): Map<String, Int> {
        val mapa =  List(5) {
            generateRandomString(Random.nextInt(5, 11)) to Random.nextInt(1, 101)
        }.toMap()
        return mapa
    }

    fun maxValue(mapa: Map<String, Int>): Int {
        return mapa.values.maxOrNull() ?: 0
    }

    fun keyForMaxValue(mapa: Map<String, Int>): String {
        return mapa.maxByOrNull { it.value }?.key ?: ""
    }

    fun sortByValueDesc(mapa: Map<String, Int>): Map<String, Int> {
        return mapa.toList().sortedByDescending { it.second }.toMap()
    }

    fun filterOddValues(mapa: Map<String, Int>): Map<String, Int> {
        return mapa.filter { it.value % 2 != 0 }
    }

    private fun generateRandomString(length: Int): String {
        val chars = ('a'..'z')
        return (1..length).map { chars.random() }.joinToString("")
    }
}
fun main() {
    val demoMap = DemoMap.createMap()
    println("Map: ")
    demoMap.forEach { (key, value) -> println("$key: $value") }

    println("Максимальное значение: ${DemoMap.maxValue(demoMap)}")
    println("Ключ для максимального значения: ${DemoMap.keyForMaxValue(demoMap)}")

    val sortedMap = DemoMap.sortByValueDesc(demoMap)
    println("Сортировка по убыванию: ")
    sortedMap.forEach { (key, value) -> println("$key: $value") }

    val filteredMap = DemoMap.filterOddValues(demoMap)
    println("Фильтрация нечетных значений: ")
    filteredMap.forEach { (key, value) -> println("$key: $value") }
}

