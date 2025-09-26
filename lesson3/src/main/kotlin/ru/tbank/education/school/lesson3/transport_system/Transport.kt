package ru.tbank.education.school.lesson3.transport_system

abstract class Transport(
    protected val name: String,
    protected val id: Int,
    protected val capacity: Int,
    protected val cost: Int,
) {
    open fun info() {
        println("ID: $id, capacity: $capacity, cost: $cost")
    }
    var passengers: Int = 0
        set(value) {
            if (value <= capacity) {
                field = value
            }
            else {
                println("Too many passengers!")
            }
        }
    abstract fun add_passangers(new : Int = 0)
    abstract fun delete_passangers(deleted : Int = 0)
    open fun Passengers() {
        println("Passengers: $passengers")
    }
}


