package ru.tbank.education.school.lesson7.lection

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun ticksElapsedFrom(year: Int): Long {
    val now = LocalDateTime.now() // зависимость от внешнего состояния (времени)
    val then = LocalDateTime.of(year, 1, 1, 0, 0)
    return ChronoUnit.NANOS.between(then, now)
}


fun ticksElapsedFrom(year: Int, now: LocalDateTime): Long {
    val then = LocalDateTime.of(year, 1, 1, 0, 0)
    return ChronoUnit.NANOS.between(then, now)
}

fun main() {
    val now = LocalDateTime.now()
    val ticks = ticksElapsedFrom(2020, now)

    println("Наносекунд прошло с начала 2020 года: $ticks")
}