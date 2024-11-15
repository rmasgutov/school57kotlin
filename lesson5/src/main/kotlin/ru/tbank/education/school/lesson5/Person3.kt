package ru.tbank.education.school.ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import com.fasterxml.jackson.annotation.JsonInclude
data class Person3(
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val passportNumber: String,
    val passportSerial: String,
    @JsonFormat(pattern = "dd-MM-yyyy")
    val birthDate: LocalDate
)
