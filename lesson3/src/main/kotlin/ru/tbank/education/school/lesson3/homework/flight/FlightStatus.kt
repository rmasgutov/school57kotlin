package ru.tbank.education.school.lesson3.homework.flight

sealed class FlightStatus {
    object Scheduled : FlightStatus()
    object Boarding : FlightStatus()
    object Flew : FlightStatus()
    object Cancelled : FlightStatus()
}