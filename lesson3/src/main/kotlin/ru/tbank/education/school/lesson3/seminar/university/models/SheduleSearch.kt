package ru.tbank.education.school.lesson3.seminar.university.models

import ru.tbank.education.school.lesson3.seminar.university.dataclasses.EventType
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.SheduleEvent
import ru.tbank.education.school.lesson3.seminar.university.interfaces.BasicSheduleSearch
import ru.tbank.education.school.lesson3.seminar.university.interfaces.EventFilter
import java.time.LocalDate
import java.util.Date

class SheduleSearch(
    private val shedule: Shedule
): BasicSheduleSearch {
    override fun getEventById(id: String): SheduleEvent? {
        return shedule.events.find { it.id == id }
    }

    override fun getEventsForDate(date: LocalDate): List<SheduleEvent> {
        val dataFilter = EventFilter { events -> events.filter { it.dateTime == date }}
        return dataFilter.filter(shedule.events)
    }

    override fun getEventsForGroup(group: Group): List<SheduleEvent> {
        val groupFilter = EventFilter { events -> events.filter { it.parcipans == group }}
        return groupFilter.filter(shedule.events)
    }

    override fun getEventsByType(type: EventType): List<SheduleEvent> {
        val typeFilter = EventFilter { events -> events.filter { it.type == type }}
        return typeFilter.filter(shedule.events)
    }

    override fun getAllEvents(): List<SheduleEvent> {
        return shedule.events
    }

}