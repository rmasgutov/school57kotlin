package ru.tbank.education.school.lesson2
import kotlin.random.Random
fun genRndString(minLen: Int, maxLen: Int): String {
    val allowedSigns = ('a'..'z') + ('A'..'Z')
    return (1..Random.nextInt(minLen, maxLen + 1)).map { allowedSigns.random() }.joinToString("")
}
object DemoMap {
    fun createMap(): Map<String, Int> {
        TODO()
        return mapOf(
            genRndString(5, 10) to Random.nextInt(1, 101),
            genRndString(5, 10) to Random.nextInt(1, 101),
            genRndString(5, 10) to Random.nextInt(1, 101),
            genRndString(5, 10) to Random.nextInt(1, 101),
            genRndString(5, 10) to Random.nextInt(1, 101)
        )
    }


    fun maxValue(): Int {
        TODO()
        return createMap().values.max()
    }

    fun keyForMaxValue(): String {
        TODO()
        fun keyForMaxValue(): String? {
            val data = createMap()
            val maxEl = data.values.max()
            return data.entries.find { it.value == maxEl }?.key
        }

        fun sortByValueDesc(): Map<String, Int> {
            TODO()
            return createMap().entries.sortedByDescending { it.value }.associateBy({ it.key }, { it.value })
        }

        fun filterOddValues(): Map<String, Int> {
            TODO()
            return createMap().filterValues { it % 2 == 1 }
        }
    }
}