package ru.tbank.education.school.lesson3.game
import kotlin.random.Random

class Monster(x : Int, y : Int, name : String, isBot : Boolean) : Character(x, y, name, isBot), Moveable {
    var hasboots = false

    constructor(
        x : Int,
        y : Int,
        name : String,
        isBot : Boolean,
        hasBoots : Boolean
    ) : this(x, y, name, isBot = false) {

        this.x = Random.nextInt()
        this.y = Random.nextInt()

    }

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }
}