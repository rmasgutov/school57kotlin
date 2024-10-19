package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
data class User5(
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val passportNumber: String? = null,
    val passportSerial: String? = null,
    val birthDate: LocalDate? = null
)
