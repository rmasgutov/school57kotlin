package ru.tbank.education.school.lesson2
//  maincraft


import kotlin.random.Random


open class Character(
    var x:Int,
    var y:Int,
    var name:String,
    val NPS:String,
    val isBot:Boolean
) {
    open fun say() {println("Hi")}
}



class Monstr(
    x:Int, y:Int, name:String, NPS:String, isBot:Boolean

) : Character(x, y, name, NPS, isBot = true), Moveable{

    constructor(name: String) : this(
        Random.nextFloat() * 100,
        Random.nextFloat() * 100,
        name
    )
    override fun move(x_2:Int, y_2:Int) {
        x += x_2
        y += y_2
        println("I'm moving to x=$x, y=$y")
    }
}


class Hero(
    x:Int, y:Int, name:String, NPS:String, isBot:Boolean,
    var hasBoots: Boolean

) : Character(x, y, name, NPS, isBot = false), Moveable {
    override fun say() {println("I'm a hero! $name")}

    constructor(name: String, hasBoots: Boolean) : this(
        Random.nextFloat() * 100,
        Random.nextFloat() * 100,
        name,
        hasBoots
    )

    override fun move(x_2:Int, y_2:Int) {
        var move_flag = 0
        if (hasBoots) {
            move_flag = 2
        } else {move_flag = 1}
        x += x_2 * move_flag
        y += y_2 * move_flag
        println("I'm moving to x=$x, y=$y")
    }
}


interface Moveable {
    fun move(dx: Int, dy: Int)
}