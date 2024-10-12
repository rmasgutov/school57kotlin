package ru.tbank.education.school.lesson3.game

import kotlin.random.Random


// Класс Monster, наследник Character
class Monster(x: Int = 0, y: Int = 0, name: String) : Character(x, y, name, isBot = true), Moveable {
    constructor(name: String) : this(Random.nextInt(0, 100), Random.nextInt(0, 100), name)

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        println("I'm moving to ${x}/${y}")
    }
}
