package ru.tbank.education.school.lesson3.seminar.university.interfaces

import ru.tbank.education.school.lesson3.seminar.university.dataclasses.EventType
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.SheduleEvent
import java.time.LocalDate

fun interface EventFilter {
    fun filter(event: List<SheduleEvent>): List<SheduleEvent>
}

fun interface EventSorter {
    fun sort(event: List<SheduleEvent>): List<SheduleEvent>
}

// Примеры SAM-Преобразований:

val lectureFilter = EventFilter { events -> events.filter { it.type == EventType.LECTURE } }
val todayFilter = EventFilter { events -> events.filter { it.dateTime == LocalDate.now() } }
val byDateSorter = EventSorter { events -> events.sortedBy { it.dateTime } }