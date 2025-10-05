package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

interface IAppointmentService {
    fun createAppointment(patient: Patient, doctor: Doctor, time: String = ""): Appointment?
    fun cancelAppointment(appointmentId: String): Boolean
    fun getAppointmentsForUser(user: User): List<Appointment>
}