package ru.tbank.education.school.lesson3.game

class Hero constructor(x:Int, y:Int, name:String, isBot:Boolean, val hasBoots:Boolean): Character(x, y, name, isBot), Moveable{
    constructor(name:String, hasBoots:Boolean) : this((0..100).random(), (0..100).random(), name, false, hasBoots) {}
    override fun say() {
        println("I'm a hero! $name")
    }
    override fun move(dx:Int, dy:Int){
        x += dx
        y += dy
        if (this.hasBoots){
            x += dx
            y += dy
        }
        println("I'm moving to ${x}/${y}")
    }
}