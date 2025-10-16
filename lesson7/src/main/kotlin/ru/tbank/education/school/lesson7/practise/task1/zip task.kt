package ru.tbank.education.school.lesson7.practise.task1

import java.time.LocalDate

/**
 * Задание: Состыкуй последовательность метрик с последовательностью дат.
 *
 * Дано: два списка одной длины:
 *  - dates: список дат
 *  - values: список измерений Double
 * Нужно: собрать список Measurement(date, value) через zip.
 *
 */
data class Measurement(val date: LocalDate, val value: Double)

fun zipMeasurements(dates: List<LocalDate>, values: List<Double>): List<Measurement> {
    TODO()
}