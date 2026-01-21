package ru.tbank.education.school.lesson7.practise.task3

/**
 * Задание 5. Проверка диапазона возраста
 *
 * Реализуй infix-функцию для проверки, попадает ли возраст в заданный диапазон.
 *
 * Пример:
 * ```
 * val age = 25
 * println(age inRange 18..30) // true
 * println(age inRange 40..50) // false
 * ```
 */

infix fun Int.inRange(range: IntRange): Boolean = this in range
