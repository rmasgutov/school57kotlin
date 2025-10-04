package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

interface IScheduleService {
    fun addDoctorSlot(doctor: Doctor, time: String)
    fun getAvailableSlots(doctor: Doctor): List<String>
}