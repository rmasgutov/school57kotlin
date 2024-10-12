package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate

/**
 * Класс Person5 представляет информацию о человеке.
 *
 * @property firstName Имя человека. Может быть null.
 * @property lastName Фамилия человека. Может быть null.
 * @property middleName Отчество человека. Может быть null.
 * @property passportNumber Номер паспорта. Может быть null.
 * @property passportSerial Серия паспорта. Может быть null.
 * @property birthDate Дата рождения человека. Может быть null.
 *
 * Аннотация @JsonInclude(JsonInclude.Include.NON_NULL) используется для того, чтобы исключить
 * из JSON-представления поля, значение которых равно null.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Person5(
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val passportNumber: String? = null,
    val passportSerial: String? = null,
    val birthDate: LocalDate? = null
)
