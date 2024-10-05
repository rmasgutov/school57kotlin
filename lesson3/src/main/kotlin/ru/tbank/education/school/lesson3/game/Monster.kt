package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Monster(xcoordinat: Int, ycoordinat: Int, name: String): Character(xcoordinat,ycoordinat,name,true){
    constructor(name: String) : this(Random.nextInt(1, 101), Random.nextInt(1, 101),"")
    override fun move(dx: Int,dy: Int){
        xcoordinat=xcoordinat + dx
        ycoordinat=ycoordinat + dy
        println("I'm moving to $xcoordinat/$ycoordinat")
    }
}