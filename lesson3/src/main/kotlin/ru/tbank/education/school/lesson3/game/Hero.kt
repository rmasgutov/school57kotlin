package ru.tbank.education.school.lesson3.game
import ru.tbank.education.school.lesson3.game.Character
import ru.tbank.education.school.lesson3.game.Moveable
import kotlin.random.Random

class Hero(xcoordinat: Int, ycoordinat: Int, name: String, isBot: Boolean): Character(xcoordinat,ycoordinat,name,false),Moveable {
    override fun move(dx: Int, dy: Int) {
        var mult = 1
        if (hasBoots) {
            mult = 2
        }
        xcoordinat = xcoordinat + dx*mult
        ycoordinat = ycoordinat + dy*mult

        println("I'm moving to $xcoordinat/$ycoordinat")
    }

    val hasBoots: Boolean = false
    override fun say() {
        println("Im a hero $name!")
    }
    constructor(name: String, HasBoots: Boolean) : this(Random.nextInt(1, 101), Random.nextInt(1, 101),name,HasBoots)
}
