package ru.tbank.education.school.lesson3.game

import kotlin.random.Random



class Hero(x: Int = 0, y: Int = 0, name: String) : Character(x, y, name, isBot = false), Moveable {
    var hasBoots = true

    constructor(name: String) : this(Random.nextInt(0, 100), Random.nextInt(0, 100), name)

    override fun say() {
        println("I'm a hero! $name")
    }

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        println("I'm moving to $x/$y")
        if (hasBoots) {
            x += dx
            y += dy
            println("Second move with boots ${x}/${y}")
        }

    }
}