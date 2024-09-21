package ru.tbank.education.school.lesson2
import kotlin.random.Random
object DemoMap {
    private val map: Map<String, Int> = createMap()
    fun getRandomString(minLength: Int, maxLength: Int): String {
        val allowedChars = ('a'..'z') + ('A'..'Z')
        return (1..Random.nextInt(minLength, maxLength + 1)).map { allowedChars.random() }.joinToString("")
    }
    fun createMap(): Map<String, Int> {
        return mapOf(
            getRandomString(5, 10) to Random.nextInt(1, 101),
            getRandomString(5, 10) to Random.nextInt(1, 101),
            getRandomString(5, 10) to Random.nextInt(1, 101),
            getRandomString(5, 10) to Random.nextInt(1, 101),
            getRandomString(5, 10) to Random.nextInt(1, 101)
        )
    }
    fun maxValue(): Int {
        return createMap().values.max()
    }
    fun keyForMaxValue(): String {
        return createMap().maxByOrNull { it.value }!!.key
    }
    fun sortByValueDesc(): Map<String, Int> {
        return map.toList().sortedByDescending { it.second }.toMap()
    }
    fun filterOddValues(): Map<String, Int> {
        return createMap().filterValues { it % 2 == 1 }
    }

}