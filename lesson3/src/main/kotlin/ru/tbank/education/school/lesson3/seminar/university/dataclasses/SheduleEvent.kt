package ru.tbank.education.school.lesson3.seminar.university.dataclasses

import ru.tbank.education.school.lesson3.seminar.university.models.Group
import java.time.LocalDate

class SheduleEvent(val id: String,
                   var fullName: String,
                   var description: String,
                   var duration: Double,
                   var type: EventType,
                   var dateTime: LocalDate,
                   var parcipans: Group
) {
}