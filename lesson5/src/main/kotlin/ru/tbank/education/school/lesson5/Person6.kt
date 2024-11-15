package ru.tbank.education.school.ru.tbank.education.school.lesson5

import java.time.LocalDate
import com.fasterxml.jackson.annotation.JsonInclude

data class Person6(
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val passportNumber: String? = null,
    val passportSerial: String? = null,
    val birthDate: LocalDate? = null
)
