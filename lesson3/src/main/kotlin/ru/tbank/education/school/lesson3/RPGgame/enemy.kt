package ru.tbank.education.school.lesson3.RPGgame

open class enemy (var name: String, protected var stats: statblock, val dmg: Float, val dmgD: Float, var hp: Float = stats.hp) {
    open fun getHit (dmg: Float) {
        hp -= dmg
    }
}

class boss(name: String, stats: statblock, dmg: Float, dmgD: Float) : enemy(name, stats, dmg, dmgD) {
    override fun getHit (dmg: Float) {
        super.getHit(dmg)
        println("Ты смог ранить эту тварь!")
    }
}