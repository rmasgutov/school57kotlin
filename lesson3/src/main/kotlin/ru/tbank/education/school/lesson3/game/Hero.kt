package ru.tbank.education.school.lesson3.game.impl

import ru.tbank.education.school.lesson3.game.Character
import ru.tbank.education.school.lesson3.game.Moveable
import kotlin.random.Random

class Hero(
    x: Int,
    y: Int,
    name: String,
    val hasBoots: Boolean
) : Character(x, y, name, false), Moveable {

    override fun say() {
        println("$name: I'm a hero!")
    }

    override fun move(dx: Int, dy: Int) {
        val multiplier = if (hasBoots) 2 else 1
        x += dx * multiplier
        y += dy * multiplier

        println("I'm moving to $x/$y")
    }

    constructor(name: String, hasBoots: Boolean) : this(Random.nextInt(), Random.nextInt(), name, hasBoots)
}