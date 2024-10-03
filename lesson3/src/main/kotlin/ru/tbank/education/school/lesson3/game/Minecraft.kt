package ru.tbank.education.school.lesson3.game

import kotlin.random.Random


interface Moveable {
    fun move(dx: Int, dy: Int)
}


abstract class Character(
    var x: Int,
    var y: Int,
    var name: String,
    var isBot: Boolean,
) {
    open fun say() {
        println("Hi!")
    }
}


class Hero(
    x: Int,
    y: Int,
    name: String,
    isBot: Boolean,
    var hasBoots: Boolean
) : Character(x, y, name, isBot), Moveable {
    override fun say() {
        println("I`m hero! $name")
    }


    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        if (hasBoots) {
            x += dx
            y += dy
        }
        val loc = x / y
        println("I`m moving to $loc")
    }


    constructor(name: String, hasBoots: Boolean) : this(Random.nextInt(), Random.nextInt(), name, false, hasBoots)
}

class Monster(
    x: Int,
    y: Int,
    name: String,
    isBot: Boolean
) : Character(x, y, name, isBot), Moveable {
    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        val loc = x / y
        println("I`m moving to $loc")
    }


    constructor(name: String) : this(Random.nextInt(), Random.nextInt(), name, true)
}