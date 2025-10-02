package ru.tbank.education.school.lesson3.RPGgame
import kotlin.math.roundToInt

open class enemy(
    val name: String,
    protected var stats: statblock,
    override val damage: Float,
    override val damageDeviation: Float,
    override var hp: Float = stats.hp
) : Damageable, Attacker {


    private val deathListener: BattleEventListener = BattleEventListener { event ->
        println("💀 $name: $event")
    }

    override fun getHit(dmg: Float) {
        hp -= dmg
        if (hp <= 0) {
            deathListener.onEvent("Я повержен!")
        }
    }

    override fun attack(target: Damageable) {

        val actualDamage = damage + (-damageDeviation.roundToInt()..damageDeviation.roundToInt()).random()
        println("$name атакует и наносит $actualDamage урона!")
        target.getHit(actualDamage)
    }

    open fun specialAbility(): String {
        return "Использует обычную атаку"
    }
}

class boss(name: String, stats: statblock, dmg: Float, dmgD: Float) : enemy(name, stats, dmg, dmgD) {
    private var enrageUsed = false

    override fun getHit(dmg: Float) {
        super.getHit(dmg)
        if (hp < stats.hp * 0.3f && !enrageUsed) {
            println("⚡ $name впадает в ЯРОСТЬ! Урон увеличен!")
            enrageUsed = true
        }
    }

    override fun specialAbility(): String {
        return "💥 Использует СМЕРТЕЛЬНЫЙ УДАР!"
    }

    override fun attack(target: Damageable) {
        if (enrageUsed) {
            val enragedDamage = damage * 1.5f
            println("${specialAbility()} Наносит $enragedDamage урона!")
            target.getHit(enragedDamage)
        } else {
            super.attack(target)
        }
    }
}