package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Monster(
    x: Float,
    y: Float,
    name: String
) : Character(x, y, name, isBot = true), Moveable {

    constructor(name: String) : this(
        Random.nextFloat() * 100,
        Random.nextFloat() * 100,
        name
    )

    override fun move(dx: Float, dy: Float) {
        this.x += dx
        this.y += dy
        println("I'm moving to x=${this.x} and y=${this.y}")
    }
}