package ru.tbank.education.school.lesson3.RPGgame

class entityInstance (var name: String, var stats: statblock) {

    fun getHit (dmg: Float) {
        stats.hp -= dmg
    }

    fun hit (entities: List<entityInstance>, targetId: Int, stamCost: Float = 0f, manaCost: Float = 0f, dmg: Float) {
        entities[targetId].getHit(dmg)
        stats.stamina-=stamCost
        stats.mana-=manaCost
    }
}