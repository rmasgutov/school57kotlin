package ru.tbank.education.school.lesson3.seminar.university.models

import ru.tbank.education.school.lesson3.seminar.university.dataclasses.EventType
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.SheduleEvent
import ru.tbank.education.school.lesson3.seminar.university.interfaces.BasicShedule
import java.time.LocalDate

class Shedule : BasicShedule {

    open val events = mutableListOf<SheduleEvent>()

    override fun addEvent(sheduleEvent: SheduleEvent) {
        events.add(sheduleEvent)
    }

    override fun updateEvent(id: String,
                             fullName: String,
                             description: String,
                             duration: Double,
                             type: EventType,
                             dateTime: LocalDate,
                             parcipans: Group) {
        for (event in events) {
            if (event.id == id) {
                event.fullName = fullName
                event.description = description
                event.duration = duration
                event.type = type
                event.dateTime = dateTime
                event.parcipans = parcipans
            }
        }
    }

    override fun removeEvent(id: String) {
        events.removeIf { it.id == id }
    }
}