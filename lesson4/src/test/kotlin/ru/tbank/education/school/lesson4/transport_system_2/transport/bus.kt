package ru.tbank.education.school.lesson3.transport_system

class Bus(id: Int, capacity: Int, cost: Int, route: Routes? = null) :
    Transport("Bus", id, capacity, cost) {
    init {
        this.route = route
    }

    override fun info() {
        super.info()
        println("Transport: $name, Route: ${route?.name}, cost: $cost")
    }
}
