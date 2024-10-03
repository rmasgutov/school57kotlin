package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Hero (x : Int, y : Int, name : String, isBot : Boolean, val hasBoots : Boolean) : Character(x, y, name, isBot), Moveable {
    constructor(name: String, hasBoots: Boolean) : this(Random.nextInt(), Random.nextInt(), name, false, hasBoots)

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
            println("I'm moving to $x/$y")
        }
    }
}
