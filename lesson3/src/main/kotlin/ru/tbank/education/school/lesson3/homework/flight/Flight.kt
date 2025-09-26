package ru.tbank.education.school.lesson3.homework.flight

import ru.tbank.education.school.lesson3.homework.person.Person

abstract class Flight(
    protected val flightNumber: String,
    private var status : FlightStatus = FlightStatus.Scheduled,
) {
    private var passengers : MutableList<Person> = mutableListOf()

    var currentStatus : FlightStatus
        get() = status
        set(value) {
            if (status == FlightStatus.Flew || status == FlightStatus.Cancelled) {
                println("Нельзя отменить вылетевший рейс")
            } else {status = value}
        }

    fun addPassenger(person: Person) {passengers.add(person)}

    fun passengersList() {
        println("Список пассажиров рейса $flightNumber")
        passengers.forEach { println("- ${it.name}, паспорт : ${it.passport}") }
    }

    abstract fun flightInfo() : String
}