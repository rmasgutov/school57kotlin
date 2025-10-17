package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

class Patient(
    name: String,
    email: String,
    password: String,
    val medicalHistory: List<String> = emptyList()
) : User(name, email, password) {
    override val role = UserRole.PATIENT


    override fun login(): Boolean {
        println("Пациент $name вошел в систему")
        return true
    }

    fun viewAppointments(appointments: List<Appointment>) {
        println("Записи пациента $name:")
        appointments.filter { it.patient == this }
            .forEach { println("- ${it.dateTime} (Доктор: ${it.doctor.formattedName})") }
    }
}