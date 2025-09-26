package ru.tbank.education.school.lesson3.transport_system

class Tube(id: Int, capacity: Int, cost: Int, val route: Route? = null) :
    Transport("Tube", id, capacity, cost) {
    override fun info() {
        super.info()
        println("Transport: $name, Route: ${route?.name}, cost: $cost")
    }
    override fun Passengers() {
        super.info()
    }
    override fun add_passangers(new: Int) {
        if (passengers + new <= capacity) {
            passengers += new
            println("Add $new passengers to train $id")
            println("Welcome to train $id")
        } else {
            println("Too many passengers!")
            println("Train $id is full")
            val extra = passengers + new - capacity
            passengers = capacity
            println("$extra passengers need to wait another train")
        }
    }
    override fun delete_passangers(deleted: Int) {
        if (deleted <= passengers) {
            passengers -= deleted
            println("Deleted $deleted passengers from train $id")
            println("Goodbye from Train $id")
        } else {
            println("Not enough passengers!")
            println("Train $id has only $passengers passengers")
        }
    }
}