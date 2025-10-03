package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets



object Hospital : IUserService, IAppointmentService, IScheduleService {
    private val users = mutableListOf<User>()
    val appointments = mutableListOf<Appointment>()


    init {
        // Тестовые данные
        val doctor1 = Doctor("иван петров", "doc1@hosp.com", "pass123", "Терапевт")
        val patient1 = Patient("анна сидорова", "pat1@hosp.com", "pass123")

        registerUser(doctor1)
        registerUser(patient1)


        doctor1.addSlot("2023-10-10 09:00")
        doctor1.addSlot("2023-10-10 10:00")
    }


    override fun registerUser(user: User): Boolean {
        if (users.any { it.getEmailAddress() == user.getEmailAddress() }) return false
        users.add(user)
        println("Пользователь ${user.formattedName} зарегистрирован")
        return true
    }

    override fun loginUser(email: String, password: String): User? {
        return users.find { it.getEmailAddress() == email && it.getPasswordValue() == password }?.apply { login() }
    }


    override fun createAppointment(patient: Patient, doctor: Doctor, time: String): Appointment? {
        val availableSlots = doctor.getAvailableSlots()
        if (availableSlots.isEmpty()) {
            println("Нет доступных слотов у доктора ${doctor.formattedName}")
            return null
        }

        // SAM-преобразование
        val timeCalculator = TimeCalculator { doc ->
            doc.getAvailableSlots().first()
        }

        val appointmentTime = if (availableSlots.contains(time)) time else timeCalculator.calculateOptimalTime(doctor)
        val appointment = Appointment(
            id = "app${appointments.size + 1}",
            patient = patient,
            doctor = doctor,
            dateTime = appointmentTime
        )

        appointments.add(appointment)
        println("Создана запись: $appointmentTime (Доктор: ${doctor.formattedName})")
        return appointment
    }

    override fun cancelAppointment(appointmentId: String): Boolean {
        val appointment = appointments.find { it.id == appointmentId }
        return if (appointment != null) {
            appointments.remove(appointment)
            println("Запись отменена")
            true
        } else false
    }

    override fun getAppointmentsForUser(user: User): List<Appointment> {
        return when (user.role) {
            UserRole.PATIENT -> appointments.filter { it.patient == user }
            UserRole.DOCTOR -> appointments.filter { it.doctor == user }
        }
    }

    override fun addDoctorSlot(doctor: Doctor, time: String) {
        doctor.addSlot(time)
        println("Добавлен слот для доктора ${doctor.formattedName}: $time")
    }

    override fun getAvailableSlots(doctor: Doctor): List<String> {
        return doctor.getAvailableSlots()
    }

    // Получения пользователей по роли
    fun getUsersByRole(role: UserRole): List<User> {
        return users.filter { it.role == role }
    }
}

