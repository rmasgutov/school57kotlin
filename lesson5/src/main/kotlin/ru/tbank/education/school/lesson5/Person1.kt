package ru.tbank.education.school.ru.tbank.education.school.lesson5

import java.time.LocalDate

data class Person1(
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val passportNumber: String,
    val passportSerial: String,
    val birthDate: LocalDate
)
