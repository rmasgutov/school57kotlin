package ru.tbank.education.school.lesson3.RPGgame

import kotlin.io.println
import kotlin.system.exitProcess

open class player (
    val name: String,
    var stats: statblock,
    var inv: MutableMap<item, Int>,
    var currentLocation: location = Village(),
    var completedLoc: Boolean = false,
    hp: Float
) {
    var hp = 100.0f
        set(value) {
            if (value <= 0) {
                print("Вы умерли!")
                exitProcess(0)
            }
        }

    fun displayInv(invent: MutableMap<item, Int>){
        val alk = invent.keys.toList()
        for (i in 0..alk.size-1) {
            val y = invent[alk[i]]
            println("${(alk[i]).itemName} - $y шт.")
        }
    }
    fun getHit (dmg: Float) {
        hp -= dmg
    }

    fun rest() {
        hp=stats.hp
    }

    fun getItem(invent: MutableMap<item, Int>, itm: item, quantity: Int = 1) {
        val alk = invent.keys.toList()
        if (itm in alk) {
            invent[itm] = invent[itm]!!.plus(quantity)
        }
        else{
            invent[itm] = quantity
        }
        println("Был получен предмет: ${itm.itemName} в количестве $quantity шт.")
    }
    fun takeItem(invent: MutableMap<item, Int?>, itm: item, quantity: Int = 1) {
        val alk = invent.keys.toList()
        if (itm in alk) {
            if (invent[itm]!! > quantity) {
            invent[itm] = invent[itm]?.minus(quantity)
            }
        }
        println("Был убран предмет: ${itm.itemName} в количестве $quantity шт.")
    }
    fun equipItem(invent: MutableMap<item, Int>, itm: item) : Map<String, Int> {
        println("Экипирован предмет: ${itm.itemName}")
        return if (itm in invent.keys.toList()){
            itm.stats
        } else {
            mapOf("hp" to 0, "power" to 0, "mana" to 0, "stamina" to 0)
        }
    }
    fun useItem(invent: MutableMap<item, Int>, itm: item): Map<String, Int> {
        invent.remove(itm)
        return (if (itm.type==1) {
            mapOf("hp" to itm.stats["hp"], "power" to itm.stats["power"])
        } else {
            mapOf("hp" to 0, "m ana" to 0, "stamina" to 0)
        }) as Map<String, Int>
    }
    fun unequipItem(invent: MutableMap<item, Int?>, itm: item) : Map<String, Int> {
        return if (itm in invent.keys.toList()){
            var temp = mutableMapOf<String, Int>()
            itm.stats.forEach { (key, value) ->
                temp[key] = value*(-1)
            }
            temp
        } else {
            mapOf("hp" to 0, "power" to 0, "mana" to 0, "stamina" to 0)
        }
    }
}