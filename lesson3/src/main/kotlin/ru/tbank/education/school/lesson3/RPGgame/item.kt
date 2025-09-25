package ru.tbank.education.school.lesson3.RPGgame
import kotlinx.serialization.Serializable
/*
1 - зелье
2 - свиток
3 - аксессуар/броня
4 - оружие
*/
@Serializable
data class item(
    val type: Int,
    val itemName: String,
    val itemDescription: String,
    val stats: Map<String, Int>,
) {
    fun useItem(type: Int, stats: Map<String, Int>): Map<String, Int?> {

        return if (type==1) {
            mapOf("hp" to stats["hp"], "power" to stats["power"])
        } else {
            mapOf("hp" to 0, "mana" to 0, "stamina" to 0)
        }
    }
}