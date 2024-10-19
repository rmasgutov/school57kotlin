package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Hero(
        x: Int,
        y: Int,
        name: String,
        private val hasBoots: Boolean
) : Character(x, y, name, false), Moveable {

    override fun say() {
        println("I'm a hero! $name")
    }

    override fun move(dx: Int, dy: Int) {
        val m: Int
        if (hasBoots) {
            m = 2
        } else {
            m = 1
        }

        x += dx * m
        y += dy * m

        println("I'm moving to $x/$y")
    }

    constructor(name: String, hasBoots: Boolean) : this(Random.nextInt(), Random.nextInt(), name, hasBoots)

}