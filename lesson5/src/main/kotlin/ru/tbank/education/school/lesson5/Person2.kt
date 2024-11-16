package ru.tbank.education.school.ru.tbank.education.school.lesson5

import java.time.LocalDate
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
data class Person2(
    val firstName: String,
    @JsonProperty("name")
    val lastName: String,
    val middleName: String?,
    val passportNumber: String,
    val passportSerial: String,
    val birthDate: LocalDate
)
//