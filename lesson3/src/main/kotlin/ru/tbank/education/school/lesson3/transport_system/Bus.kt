package ru.tbank.education.school.lesson3.transport_system

class Bus(id: Int, capacity: Int, cost: Int, val route: Route? = null) :
    Transport("Bus", id, capacity, cost) {
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
            println("Add $new passengers to bus $id")
            println("Welcome to bus $id")
        } else {
            println("Too many passengers!")
            println("Bus $id is full")
            val extra = passengers + new - capacity
            passengers = capacity
            println("$extra passengers need to wait another bus")
        }
    }
    override fun delete_passangers(deleted: Int) {
        if (deleted <= passengers) {
            passengers -= deleted
            println("Deleted $deleted passengers from bus $id")
            println("Goodbye from bus $id")
        } else {
            println("Not enough passengers!")
            println("Bus $id has only $passengers passengers")
        }
    }
}