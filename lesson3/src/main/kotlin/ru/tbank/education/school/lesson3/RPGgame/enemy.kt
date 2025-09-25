package ru.tbank.education.school.lesson3.RPGgame

open class enemy (var name: String, var stats: statblock, val dmg: Float, val dmgD: Float, var hp: Float = stats.hp) {
    fun getHit (dmg: Float) {
        hp -= dmg
    }
}