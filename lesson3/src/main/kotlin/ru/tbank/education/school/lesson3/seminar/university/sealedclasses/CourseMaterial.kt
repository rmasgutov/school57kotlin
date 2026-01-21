package ru.tbank.education.school.lesson3.seminar.university.sealedclasses

import java.time.LocalDateTime

sealed class CourseMaterial {
    data class Document(val title: String, val content: String, val size: Int) : CourseMaterial()
    data class Video(val title: String, val duration: Double, val url: String) : CourseMaterial()
    data class Assignment(val title: String, val deadline: LocalDateTime, val maxScore: Int) : CourseMaterial()
    object Empty : CourseMaterial()
}