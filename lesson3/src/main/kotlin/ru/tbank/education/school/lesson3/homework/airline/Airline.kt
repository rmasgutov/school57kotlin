package ru.tbank.education.school.lesson3.homework.airline

import ru.tbank.education.school.lesson3.homework.flight.Flight

class Airline (val name : String){
    private val flights : MutableList<Flight> = mutableListOf()

    fun addFFlight(flight : Flight) {flights.add(flight)}

    fun listFlights() {
        println("Авиакомпания $name выполняет или выполнила рейсы")
        flights.forEach { println("- ${it.flightInfo()}") }
    }
}