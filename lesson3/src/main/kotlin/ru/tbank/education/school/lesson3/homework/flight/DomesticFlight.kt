package ru.tbank.education.school.lesson3.homework.flight

class DomesticFlight (
    flightNumber: String,
    private val region: String
) : Flight(flightNumber) {
    constructor(flightNumber: String) : this(flightNumber, "Неизвестный регион")

    override fun flightInfo(): String {
        return "Внутренний рейс $flightNumber в регион $region. Статус : $currentStatus"
    }
}
