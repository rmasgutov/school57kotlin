package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

fun println(str: String) {
    val writer = OutputStreamWriter(System.out, StandardCharsets.UTF_8)
    writer.write((str+"\n"))
    writer.flush()
}
fun main() {

    // Регистрация врача
    val doctor = Doctor(
        name = "Иван Петров",
        email = "doc1@hosp.com",
        password = "pass123",
        specialization = "Терапевт"
    )
    Hospital.registerUser(doctor)
    doctor.addSlot("12-12-2012 10:00")
    doctor.addSlot("12-12-2012 12:00")
// Регистрация пациента
    val patient = Patient(
        name = "Анна Сидорова",
        email = "pat1@hosp.com",
        password = "pass123"
    )
    Hospital.registerUser(patient)
    Hospital.createAppointment(patient, doctor, "52-52-52 52:52")
}
