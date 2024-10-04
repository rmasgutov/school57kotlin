package ru.tbank.education.school.lesson3.game

abstract class Character(var x: Int, var y: Int, var name: String, var isBot: Boolean) {
    abstract fun say()
}

class Hero(x: Int, y: Int, name: String) : Character(x, y, name, false) {
    var hasBoots: Boolean = true

    constructor(x: Int, y: Int, name: String, hasBoots: Boolean) : this(x, y, name) {
        this.hasBoots = hasBoots
    }

    override fun say() {
        println("I'm a hero! $name")
    }

    fun move(dx: Int, dy: Int) {
        if (hasBoots) {
            x += dx * 2
            y += dy * 2
        } else {
            x += dx
            y += dy
        }
        println("I'm moving to $x/$y")
    }
}

class Monster(x: Int, y: Int, name: String) : Character(x, y, name, true) {
    override fun say() {
        println("Hi!")
    }

    fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        println("I'm moving to $x/$y")
    }
}
interface Moveable {
    fun move(dx: Int, dy: Int)
}

fun main() {
    val hero = Hero(0, 0, "Arthur")
    hero.say()
    hero.move(1, 1)

    val monster = Monster(5, 5, "Goblin")
    monster.say()
    monster.move(-2, -2)
}
