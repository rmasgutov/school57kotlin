package ru.tbank.education.school.ru.tbank.education.school.lesson5
import com.fasterxml.jackson.annotation.JsonSetter
import java.time.LocalDate

data class Person2(
    @JsonSetter("name")
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val passportNumber: String,
    val passportSerial: String,
    val birthDate: LocalDate,

)