package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Monster(
    x: Int, y: Int, name: String,
) : Character(x, y, name, false), Movable {
    constructor(name: String) : this(Random.nextInt(), Random.nextInt(), name)

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        println("I'm moving to $x/$y")
    }
}