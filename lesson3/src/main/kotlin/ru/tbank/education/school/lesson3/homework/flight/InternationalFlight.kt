package ru.tbank.education.school.lesson3.homework.flight

class InternationalFlight (
    flightNumber: String,
    private val country : String,
) : Flight(flightNumber) {
    constructor(flightNumber: String) : this(flightNumber, "Неизвестная страна")

    override fun flightInfo(): String {
        return "Международный рейс $flightNumber в страну $country. Статус : $currentStatus"
    }
}