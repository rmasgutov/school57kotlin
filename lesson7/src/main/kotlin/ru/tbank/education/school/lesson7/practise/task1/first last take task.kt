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
    var counter = 0
    var first = false
//    val second: MutableList<Event> = mutableListOf()
//    val third: MutableList<Event> = mutableListOf()
//    events.forEach {if (it.type == EventType.ERROR) first = true; if (second.size < 2 && it.type == EventType.LOGIN) second.add(it); if (third.size < nToday && it.date.toLocalDate() == LocalDate.now()) third.add(it)} // За 1 проход
    return Triple(
        try { events.find { it.type == EventType.ERROR } } catch (e: Throwable) { null },
        try { events.filter { it.type == EventType.LOGIN }.takeLast(2) } catch (e: Throwable) { emptyList() },
        try { events.filter { it.date.toLocalDate() == LocalDate.now() }.take(nToday) } catch (e: Throwable) { emptyList() }
    )
}