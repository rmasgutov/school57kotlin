package ru.tbank.education.school.lesson7.practise.task3

/**
 * Задание 1. Инфиксная фильтрация пользователей
 *
 * Реализуй infix-функции для класса [User], чтобы можно было фильтровать пользователей так,
 * как будто мы пишем на естественном языке.
 *
 * Пример использования:
 * ```
 * val result = users.filter { it ageGreaterThan 25 andLivesIn "Москва" }
 * ```
 */

data class User(val name: String, val age: Int, val city: String)