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
) : Usable {

    override fun use(user: player): Boolean {
        return when (type) {
            1 -> { // зелье лечения
                val healAmount = stats["hp"]?.toFloat() ?: 0f
                user.hp += healAmount
                println("💚 Использовано зелье лечения! +${healAmount.toInt()} HP")
                true
            }
            2 -> { // зелье силы
                val powerBoost = stats["power"]?.toFloat() ?: 0f
                user.stats.power += powerBoost
                println("💪 Использовано зелье силы! +${powerBoost.toInt()} к атаке")
                true
            }
            else -> {
                println("Этот предмет нельзя использовать")
                false
            }
        }
    }

    constructor(type: Int, name: String) : this(
        type, name, "Описание $name",
        when (type) {
            1 -> mapOf("hp" to 30)
            2 -> mapOf("power" to 5)
            3 -> mapOf("hp" to 20, "power" to 3)
            4 -> mapOf("power" to 10)
            else -> mapOf()
        }
    )
}