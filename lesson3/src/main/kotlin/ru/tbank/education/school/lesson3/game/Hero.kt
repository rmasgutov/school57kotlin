package ru.tbank.education.school.lesson3.game
import ru.tbank.education.school.lesson3.game.Character
import ru.tbank.education.school.lesson3.game.Moveable
import kotlin.random.Random

class Hero(x:Int,y:Int,name:String,isBot:Boolean):Character(x,y,name, false),Moveable {
    val HasBoots: Boolean = false
    override fun say() {
        println("Im a hero $name!")
    }
    override fun move(dx:Int,dy:Int) {
        var mult: Int = 1
        if (HasBoots) {
            mult = 2
        }
        x = x + dx*mult
        y = y + dy*mult
        println("I'm moving to $x/$y")
    }
    constructor(name: String, HasBoots:Boolean) : this(Random.nextInt(1,101), Random.nextInt(1,101),name,HasBoots)
}