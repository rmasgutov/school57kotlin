package ru.tbank.education.school.lesson3.seminar.university.interfaces

import jdk.jfr.Event
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.Conflict
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.EventType
import ru.tbank.education.school.lesson3.seminar.university.models.Group
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.SheduleEvent
import java.time.LocalDate
import java.util.Date

interface BasicShedule {
    fun addEvent(sheduleEvent: SheduleEvent)
    fun updateEvent(id: String,
                    fullName: String,
                    description: String,
                    duration: Double,
                    type: EventType,
                    dateTime: LocalDate,
                    parcipans: Group)
    fun removeEvent(id: String)
}

interface BasicSheduleSearch {
    fun getEventById(id: String): SheduleEvent?
    fun getEventsForDate(date: LocalDate): List<SheduleEvent>
    fun getEventsForGroup(group: Group): List<SheduleEvent>
    fun getEventsByType(type: EventType): List<SheduleEvent>
    fun getAllEvents(): List<SheduleEvent>
}

interface SheduleConflictsChecker {
    fun checkConflicts(sheduleEvent: SheduleEvent): List<Conflict>
}