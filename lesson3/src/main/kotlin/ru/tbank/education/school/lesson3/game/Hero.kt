package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Hero : Character, Movable {
    override val isBot = false

    private var hasBoots: Boolean

    constructor(name: String, hasBoots: Boolean) {
        this.name = name
        this.hasBoots = hasBoots
        x = Random.nextDouble(-30_000_000.0, 30_000_000.0)
        y - Random.nextDouble(-30_000_000.0, 30_000_000.0)
    }
    override fun say() {
        println("I'm a hero! $name")
    }

    override fun move(dx: Double, dy: Double) {
        var xNew = this.x + dx
        var yNew = this.y + dy
        if (hasBoots) {
            xNew += dx
            yNew += dy
        }

        println("I'm moving to $x/$y")
        this.x = xNew
        this.y = yNew
    }
}
