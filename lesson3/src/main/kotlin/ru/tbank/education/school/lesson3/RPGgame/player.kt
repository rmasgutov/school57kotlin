package ru.tbank.education.school.lesson3.RPGgame

import kotlin.math.roundToInt
import kotlin.system.exitProcess

class player(
    val name: String,
    var stats: statblock,
    var inv: MutableMap<item, Int>,
    var currentLocation: location = Village(),
    var completedLoc: Boolean = false,
    override var hp: Float = 100F
) : Damageable, Attacker {

    /*override var hp: Float = 100.0f
        set(value) {
            if (value <= 0) {
                print("Вы умерли!")
                exitProcess(0)
            }
        }*/

    private val equippedItems = mutableMapOf<String, item>()

    // Функциональный интерфейс для обработки получения предметов
    private val itemGetListener: BattleEventListener = BattleEventListener { itemName ->
        println("🎁 Получен предмет: $itemName")
    }

    override val damage: Float
        get() {
            return stats.power + (equippedItems.values.sumOf { (it.stats["power"]?.toFloat() ?: 0f).toInt() })
        }

    override val damageDeviation: Float
        get() = damage * 0.1f

    override fun getHit(dmg: Float) {
        hp -= dmg
        println("Вы получаете $dmg урона! Осталось HP: ${hp.toInt()}")
    }

    override fun attack(target: Damageable) {
        val actualDamage = damage + (-damageDeviation.roundToInt()..damageDeviation.roundToInt()).random()
        println("Вы атакуете и наносите ${actualDamage.toInt()} урона!")
        target.getHit(actualDamage)
    }

    private fun onDeath() {
        println("Вы умерли! Игра окончена.")
        exitProcess(0)
    }

    fun rest() {
        hp = stats.hp
        println("Вы отдохнули. HP восстановлено до $hp")
    }

    fun displayInv() {
        if (inv.isEmpty()) {
            println("Инвентарь пуст")
            return
        }
        println("=== ИНВЕНТАРЬ ===")
        inv.forEach { (item, quantity) ->
            println("${item.itemName} - $quantity шт.")
        }
    }

    fun getItem(itm: item, quantity: Int = 1) {
        inv[itm] = inv.getOrDefault(itm, 0) + quantity
        itemGetListener.onEvent("${itm.itemName} x$quantity")
    }

    fun useItem(itm: item): Boolean {
        if ((inv[itm] ?: 0) > 0) {
            val used = itm.use(this)
            if (used) {
                inv[itm] = inv[itm]!! - 1
                if (inv[itm] == 0) inv.remove(itm)
                return true
            }
        } else {
            println("Предмет '${itm.itemName}' не найден в инвентаре")
        }
        return false
    }

    fun equipItem(itm: item): Boolean {
        if (itm.type == 3 || itm.type == 4) { // броня или оружие
            equippedItems[itm.itemName] = itm
            println("✅ Экипирован: ${itm.itemName}")
            return true
        }
        println("Этот предмет нельзя экипировать")
        return false
    }
}