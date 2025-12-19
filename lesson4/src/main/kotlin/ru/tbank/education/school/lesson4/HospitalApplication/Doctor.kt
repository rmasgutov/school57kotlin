package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

class Doctor(
    name: String,
    email: String,
    password: String,
    val specialization: String,
    private val schedule: Schedule = Schedule()
) : User(name, email, password) {
    override val role = UserRole.DOCTOR

    override fun login(): Boolean {
        println("Доктор $name вошел в систему")
        return true
    }

    fun viewAppointments(appointments: List<Appointment>) {
        println("Записи к доктору $name:")
        appointments.filter { it.doctor == this }
            .forEach { println("- ${it.dateTime} (Пациент: ${it.patient.formattedName})") }
    }

    fun getAvailableSlots(): List<String> = schedule.getAvailableSlots()

    fun addSlot(time: String) {
        schedule.addSlot(time)
    }
}