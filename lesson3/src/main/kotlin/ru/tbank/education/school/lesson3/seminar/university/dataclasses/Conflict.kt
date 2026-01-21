package ru.tbank.education.school.lesson3.seminar.university.dataclasses

import ru.tbank.education.school.lesson3.seminar.university.dataclasses.SheduleEvent

data class Conflict(
    val type: EventType,
    val message: String,
    val conflictingEvent: SheduleEvent?,
)
