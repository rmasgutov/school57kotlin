package ru.tbank.education.school.lesson3.transport_system

abstract class Transport(
    protected val name: String,
    protected val id: Int,
    protected val capacity: Int,
    protected val cost: Int
) : Info, Passengers, Routeable {

    override var route: Routes? = null
    override fun info() {
        println("ID: $id, capacity: $capacity, cost: $cost, transport: $name, route: ${route?.name}")
    }
    protected var passengers: Int = 0
        set(value) {
            field = when {
                value < 0 -> 0
                value <= capacity -> value
                else -> capacity
            }
        }
    private var passengersNotifier: PassengersNotifier? = null
    fun setPassengersNotifier(listener: PassengersNotifier?) {
        this.passengersNotifier = listener
    }
    protected fun notifyPassengersChange() {
        passengersNotifier?.change(passengers)
    }
    open fun Passengers() {
        println("Passengers: $passengers")
    }
    override fun add_passangers(new: Int) {
        if (passengers + new <= capacity) {
            passengers += new
            println("Add $new passengers to $name $id")
            println("Welcome to $name $id")
        } else {
            println("Too many passengers!")
            println("$name $id is full")
            val extra = passengers + new - capacity
            passengers = capacity
            println("$extra passengers need to wait another $name")
        }
        notifyPassengersChange()
    }
    override fun delete_passangers(deleted: Int) {
        if (deleted <= passengers) {
            passengers -= deleted
            println("Deleted $deleted passengers from $name $id")
            println("Goodbye from $name $id")
        } else {
            println("Not enough passengers!")
            println("$name $id has only $passengers passengers")
        }
        notifyPassengersChange()
    }
}