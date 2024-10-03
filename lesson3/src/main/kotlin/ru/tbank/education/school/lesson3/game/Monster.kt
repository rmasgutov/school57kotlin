package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Monster (x : Int, y : Int, name: String, isBot : Boolean) : Character(x, y, name, isBot), Moveable {
    constructor(name: String) : this(Random.nextInt(), Random.nextInt(), name, false)

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        println("I'm moving to $x/$y")
    }
}
