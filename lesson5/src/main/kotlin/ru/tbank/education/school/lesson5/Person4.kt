package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate
import java.util.*

/**
 * Класс Person4 представляет информацию о человеке.
 *
 * @property firstName Имя человека.
 * @property lastName Фамилия человека.
 * @property middleName Отчество человека. Может быть отсутствующим (используется Optional).
 * @property passportNumber Номер паспорта.
 * @property passportSerial Серия паспорта.
 * @property birthDate Дата рождения человека.
 *
 * Аннотация @JsonInclude(JsonInclude.Include.NON_ABSENT) используется для того, чтобы исключить
 * из JSON-представления отсутствующие поля (например, отчество).
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
data class Person4(
    val firstName: String,
    val lastName: String,
    val middleName: Optional<String>,
    val passportNumber: String,
    val passportSerial: String,
    val birthDate: LocalDate
)
