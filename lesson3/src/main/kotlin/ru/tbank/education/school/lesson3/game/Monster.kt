package ru.tbank.education.school.lesson3.game

class Monster(x:Int, y:Int, name:String, isBot:Boolean): Character(x, y, name, isBot), Moveable{
    constructor(name:String) : this((0..100).random(), (0..100).random(), name, true) {}
    override fun move(dx:Int, dy:Int){
        x += dx
        y += dy
        println("I'm moving to ${x}/${y}")
    }
}