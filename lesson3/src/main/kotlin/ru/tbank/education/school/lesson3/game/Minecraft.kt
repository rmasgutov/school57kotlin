package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

// Абстрактный класс Character
abstract class Character(
    var x: Int,
    var y: Int,
    val name: String,
    val isBot: Boolean
) {
    open fun say() {
        println("Hi!")
    }
}

// Класс Hero, наследник Character
class Hero(x: Int = 0, y: Int = 0, name: String) : Character(x, y, name, isBot = false), Moveable {
    var hasBoots: Boolean = true

    constructor(name: String) : this(Random.nextInt(0, 100), Random.nextInt(0, 100), name)

    override fun say() {
        println("I'm a hero! My name is $name")
    }

    override fun move(dx: Int, dy: Int) {
        if (hasBoots) {
            x += dx * 2
            y += dy * 2
            println("I'm moving to ${x}/${y} with boots")
        } else {
            x += dx
            y += dy
            println("I'm moving to ${x}/${y}")
        }
    }
}

// Класс Monster, наследник Character
class Monster(x: Int = 0, y: Int = 0, name: String) : Character(x, y, name, isBot = true), Moveable {
    constructor(name: String) : this(Random.nextInt(0, 100), Random.nextInt(0, 100), name)

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        println("I'm moving to ${x}/${y}")
    }
}

// Интерфейс Moveable
interface Moveable {
    fun move(dx: Int, dy: Int)
}

fun main() {
    val hero = Hero("Aragorn")
    val monster = Monster("Orc")

    hero.say()
    monster.say()

    hero.move(10, 20)
    monster.move(15, 25)

    hero.hasBoots = false
    hero.move(10, 20)
    hero.x = 0
    hero.move(0, 20)
}