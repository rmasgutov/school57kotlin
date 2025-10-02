package ru.tbank.education.school.lesson3.RPGgame

// Основные интерфейсы
interface Damageable {
    var hp: Float
    fun getHit(dmg: Float)
}

interface Attacker {
    val damage: Float
    val damageDeviation: Float
    fun attack(target: Damageable)
}

interface Usable {
    fun use(user: player): Boolean
}

// Функциональные интерфейсы
fun interface BattleEventListener {
    fun onEvent(event: String)
}

fun interface LootGenerator {
    fun generateLoot(): List<item>
}