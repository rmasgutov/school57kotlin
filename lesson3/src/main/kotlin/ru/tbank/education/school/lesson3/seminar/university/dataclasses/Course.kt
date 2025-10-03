package ru.tbank.education.school.lesson3.seminar.university.dataclasses

class Course(
    val id: String,
    val name: String,
    val description: String,
    val credits: Double,) {

    fun getInfo(): String {
        return "Название: $name, Описание: $description, Стоимость: $credits"
    }
}