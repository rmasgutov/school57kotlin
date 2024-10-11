package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Hero(
    x: Float,
    y: Float,
    name: String,
    val hasBoots: Boolean
) : Character(x, y, name, isBot = false), Moveable {

    override fun say() {
        println("I'm a hero! $name")
    }

    constructor(name: String, hasBoots: Boolean) : this(
        Random.nextFloat() * 100,
        Random.nextFloat() * 100,
        name,
        hasBoots
    )

    override fun move(dx: Float, dy: Float) {
        val can_move = if (hasBoots) 2 else 1
        this.x += can_move * dx
        this.y += can_move * dy
        println("I'm moving to x=${this.x} and y=${this.y}")
    }
}