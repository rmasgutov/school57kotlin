package ru.tbank.education.school.ru.tbank.education.school.lesson5

import java.time.LocalDate
import java.util.*

data class Person4(
    val firstName: String,
    val lastName: String,
    val middleName: Optional<String>,
    val passportNumber: String,
    val passportSerial: String,
    val birthDate: LocalDate
)
