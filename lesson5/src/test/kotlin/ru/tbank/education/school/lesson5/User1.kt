package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class User1 (
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val birthDate: LocalDate
)


