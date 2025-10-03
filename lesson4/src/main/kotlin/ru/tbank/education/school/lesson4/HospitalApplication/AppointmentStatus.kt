package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

sealed class AppointmentStatus {
    object SCHEDULED : AppointmentStatus()
    object CANCELLED : AppointmentStatus()
    object COMPLETED : AppointmentStatus()
}