package ru.tbank.education.school.lesson3.seminar.university

import ru.tbank.education.school.lesson3.seminar.university.dataclasses.EventType
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.SheduleEvent
import ru.tbank.education.school.lesson3.seminar.university.interfaces.EventFilter
import ru.tbank.education.school.lesson3.seminar.university.models.Group
import ru.tbank.education.school.lesson3.seminar.university.models.Shedule
import ru.tbank.education.school.lesson3.seminar.university.models.SheduleSearch
import ru.tbank.education.school.lesson3.seminar.university.people.Lecturer
import java.time.LocalDate

fun main() {
    val schedule = Shedule()
    val scheduleSearch = SheduleSearch(schedule)

    val lecturer = Lecturer("1", "Boba Goroh", "g@y.r", "Math", "Goi")
    val studentGroup = Group("1", "group1")

    val mathLecture = SheduleEvent("1",
        "Math Lecture",
        "You all will rip",
        2.5,
        EventType.LECTURE,
        LocalDate.now(),
        studentGroup)

    val physLecture = SheduleEvent("2",
        "Physics Lecture",
        "You all will rip",
        2.5,
        EventType.LECTURE,
        LocalDate.of(2025, 11, 2),
        studentGroup)

    schedule.addEvent(mathLecture)
    schedule.addEvent(physLecture)

    val lectureFilter = EventFilter { events -> events.filter {it.type == EventType.LECTURE}}
    val todayFilter = EventFilter {events -> events.filter {it.dateTime == LocalDate.of(2025, 11, 2)}}

    val lectures = lectureFilter.filter(schedule.events)
    println(lectures.size)

    val todayEvents = todayFilter.filter(schedule.events)
    println(todayEvents.size)

    val lecturesFound = scheduleSearch.getEventsByType(EventType.LECTURE)
    println(lecturesFound.size)
}