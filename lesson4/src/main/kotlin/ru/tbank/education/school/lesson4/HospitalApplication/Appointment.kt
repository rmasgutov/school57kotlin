package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

data class Appointment(
    val id: String,
    val patient: Patient,
    val doctor: Doctor,
    val dateTime: String,
    val status: AppointmentStatus = AppointmentStatus.SCHEDULED
)
