package ru.tbank.education.school.lesson7.practise.task3

import java.time.LocalDate

/**
 * Задание 4. Дата через infix
 *
 * Реализуй infix-функцию, которая создаёт дату, добавляя указанное количество дней к текущему моменту.
 *
 * Пример:
 * ```
 * val deadline = 5 daysFromNow
 * println(deadline) // Текущая дата + 5 дней
 * ```
 */

infix fun Int.daysFromNow(ignore: Unit): LocalDate = LocalDate.now().plusDays(this.toLong())
