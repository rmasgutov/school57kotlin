package ru.tbank.education.school.lesson7.practise.task1

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Задание: Из ленты событий выдели:
 *  - первое событие типа ERROR,
 *  - последние два события типа LOGIN,
 *  - первые N событий за сегодня.
 *
 * Верни тройку: Triple<Event?, List<Event>, List<Event>>, где по порядку будет:
 *  - первое событие типа ERROR,
 *  - последние два события типа LOGIN,
 *  - первые N событий за сегодня.
 *
 */
enum class EventType { LOGIN, LOGOUT, ERROR, INFO }
data class Event(val type: EventType, val date: LocalDateTime)

fun sliceEvents(
    events: List<Event>,
    nToday: Int
): Triple<Event?, List<Event>, List<Event>> {
    TODO()
}