package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Hero(
    x: Int, y: Int, name: String,
    var hasBoots: Boolean
) : Character(x, y, name, false), Movable {

    constructor(name: String, hasBoots: Boolean) : this(Random.nextInt(), Random.nextInt(), name, hasBoots)

    override fun say() {
        println("I'm hero! $name")
    }

    override fun move(dx: Int, dy: Int) {
        val mult = hasBoots.compareTo(false)
        x += dx * mult
        y += dy * mult
        println("I'm moving to $x/$y")
    }
}