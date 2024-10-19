package ru.tbank.education.school.ru.tbank.education.school.lesson5
import com.fasterxml.jackson.annotation.JsonProperty


data class Person7(
    @JsonProperty("name")
    val firstName: String,
    val lastName: String,
    val middleName: String,
)
