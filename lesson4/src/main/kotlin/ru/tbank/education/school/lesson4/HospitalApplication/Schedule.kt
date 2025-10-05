package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

class Schedule {
    private val slots = mutableListOf<String>()

    fun addSlot(time: String) {
        slots.add(time)
    }

    fun getAvailableSlots(): List<String> = slots.filter { slot ->
        !Hospital.appointments.any { it.dateTime == slot }
    }
}