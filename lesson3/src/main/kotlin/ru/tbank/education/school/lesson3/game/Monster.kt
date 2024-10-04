package ru.tbank.education.school.lesson3.game

class Monster(x:Int, y:Int, name:String, isBot:Boolean): Character(x, y, name, isBot), Moveable{
    constructor(name:String, hasBoots:Int) : this((0..100).random(), (0..100).random(), name, true) {}
    override fun move(dx:Int, dy:Int){
        this.x += dx
        this.y += dy
        println("I'm moving to ${this.x}/${this.y}")
    }
}