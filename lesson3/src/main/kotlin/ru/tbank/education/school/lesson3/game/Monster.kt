package ru.tbank.education.school.lesson3.game

import ru.tbank.education.school.lesson3.game.Character
import ru.tbank.education.school.lesson3.game.Moveable
import kotlin.random.Random
class Monster(x:Int,y:Int,name:String):Character(x,y,name, true),Moveable {
    constructor(name: String) : this(Random.nextInt(1, 101), Random.nextInt(1, 101), name)
    override fun move(dx: Int, dy: Int) {
        x = x + dx
        y = y + dy
        println("I'm moving to $x/$y")
    }

}