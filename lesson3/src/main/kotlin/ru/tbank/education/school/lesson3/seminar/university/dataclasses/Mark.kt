package ru.tbank.education.school.lesson3.seminar.university.dataclasses

import Lecturer
import ru.tbank.education.school.lesson3.seminar.university.people.Student
import java.time.LocalDateTime

class Mark (
    val id: String,
    val from: Lecturer,
    val to: Student,
    val value: Int,
    val discription: String,
    val date: LocalDateTime = LocalDateTime.now()
) {
    init {
        require(value in 1..12) { "Оценка должна быть от 1 до 12" }
    }

    fun sendAndSave(): Boolean {
        return try {
            to.addMark(this)
            from.addGivenMark(this)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun toString(): String {
        return this.value.toString()
    }
}