package ru.tbank.education.school.lesson3.game


import kotlin.random.Random


class Hero(
    x: Int,
    y: Int,
    name: String,
    isBot: Boolean = false,
    val hasBoots: Boolean
) : Character(x, y, name, isBot), Moveable {
    override fun say() {
        println("I`m hero! $name")
    }


    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        if (hasBoots) {
            x += dx
            y += dy
        }
        println("I`m moving to $x / $y")
    }


    constructor(name: String, hasBoots: Boolean) : this(Random.nextInt(), Random.nextInt(), name, false, hasBoots)
}