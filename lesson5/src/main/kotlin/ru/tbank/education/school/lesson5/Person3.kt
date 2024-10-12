package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

/**
 * Класс Person3 представляет информацию о человеке.
 *
 * @property firstName Имя человека.
 * @property lastName Фамилия человека.
 * @property middleName Отчество человека. Может быть null.
 * @property passportNumber Номер паспорта.
 * @property passportSerial Серия паспорта.
 * @property birthDate Дата рождения человека в формате "дд-ММ-гггг".
 *
 * Аннотация @JsonFormat используется для указания формата даты при сериализации и десериализации.
 */
data class Person3(
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val passportNumber: String,
    val passportSerial: String,
    @JsonFormat(pattern = "dd-MM-yyyy")
    val birthDate: LocalDate
)
