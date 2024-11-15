package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

/**
 * Класс Person2 представляет информацию о человеке.
 *
 * @property firstName Имя человека, сериализуемое как "name".
 * @property lastName Фамилия человека.
 * @property middleName Отчество человека. Может быть null.
 * @property passportNumber Номер паспорта.
 * @property passportSerial Серия паспорта.
 * @property birthDate Дата рождения человека.
 *
 * Аннотация @JsonProperty используется для изменения имени поля при сериализации и десериализации.
 */
data class Person2(
    @JsonProperty("name")
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val passportNumber: String,
    val passportSerial: String,
    val birthDate: LocalDate
)
