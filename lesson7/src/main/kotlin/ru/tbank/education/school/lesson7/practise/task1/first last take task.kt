package ru.tbank.education.school.lesson7.practise.task1// 7) FIRST / LAST / TAKE — работа с временными событиями
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
data class Event(val type: EventType, val ts: Long) // ts — epoch millis

fun sliceEvents(
    events: List<Event>,
    nToday: Int,
    isToday: (Long) -> Boolean
): Triple<Event?, List<Event>, List<Event>> {
    TODO()
}