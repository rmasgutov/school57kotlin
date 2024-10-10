package ru.tbank.education.school.lesson3.game


import kotlin.random.Random


class Monster(
    x: Int,
    y: Int,
    name: String,
    isBot: Boolean = true
) : Character(x, y, name, isBot), Moveable {
    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        println("I`m moving to $x / $y")
    }


    constructor(name: String) : this(Random.nextInt(), Random.nextInt(), name)
}