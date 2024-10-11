package ru.tbank.education.school.lesson2

abstract class Bus(
    var people: Int,
    val color: String,
    var velocity: Int,
    val weight: Int,
    val engine: Engine
) {
    open fun go() {
        println("Now there are $people people in the bus")
        velocity += 15
        println("The speed is up and now the velocity is $velocity")
    }
}

class Engine(
    val capacity: Int,
    var refilling: Int,
    val model: String
){
    fun start(){
        println("The engine started work")
    }
    fun refill(){
         refilling += 50
    }
    fun stop(){
        println("The engine stopped work")
    }

}

class Electrobus(
    people: Int, color: String, velocity: Int, weight: Int, engine: Engine,
    val level_of_noise: Int,
    var time_before_refill: Int
): Bus(people, color, velocity, weight, engine){
    fun run(){
        time_before_refill -= 30
    }
    override fun go(){
        velocity += 30
    }
}