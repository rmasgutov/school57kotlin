package ru.tbank.education.school.lesson3.homework

import ru.tbank.education.school.lesson3.homework.airline.Airline
import ru.tbank.education.school.lesson3.homework.flight.DomesticFlight
import ru.tbank.education.school.lesson3.homework.flight.Flight
import ru.tbank.education.school.lesson3.homework.flight.FlightStatus
import ru.tbank.education.school.lesson3.homework.flight.InternationalFlight
import ru.tbank.education.school.lesson3.homework.person.Person

fun main() {
    // Создаем авиакомпанию
    var escape : Airline = Airline("escape")

    // Создаем перелёты
    val flightToMoscow : DomesticFlight = DomesticFlight("X228", "Moscow")
    val flightToKazakhstan : InternationalFlight = InternationalFlight("I255", "Kazakhstan")
    val flightToUSA : InternationalFlight = InternationalFlight("MIT100", "USA")

    // Создаём пассажира
    val passenger : Person = Person("Lavr, I", "1884")

    // Добавляем перелёты авиакомпании
    escape.addFFlight(flightToMoscow)
    escape.addFFlight(flightToUSA)
    escape.addFFlight(flightToUSA)

    escape.listFlights()

    flightToMoscow.flightInfo()
    flightToKazakhstan.currentStatus = FlightStatus.Boarding

    flightToUSA.currentStatus = FlightStatus.Flew
    flightToUSA.currentStatus = FlightStatus.Boarding
    flightToUSA.flightInfo()
}