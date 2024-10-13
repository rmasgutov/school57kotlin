package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Monster(name: String) : Character(
    Random.nextDouble(-30_000_000.0, 30_000_000.0),
    Random.nextDouble(-30_000_000.0, 30_000_000.0),
    name,
    true
), Moveable {

    override fun move(dx: Double, dy: Double) {
        val xNew = this.x + dx
        val yNew = this.y + dy

        println("I'm moving to $x/$y")
        this.x = xNew
        this.y = yNew
    }
}
