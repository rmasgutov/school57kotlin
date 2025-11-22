package ru.tbank.education.school.lesson7.practise.task3

/**
 * Задание 1. Инфиксная фильтрация пользователей
 *
 * Реализуй infix-функции для класса [User], чтобы можно было фильтровать пользователей так,
 * как будто мы пишем на естественном языке.
 *
 * Пример использования:
 * ```
 * val result = users.filter { it ageGreaterThan 25 }
 * val result = users.filter { it livesIn "Москва" }
 * ```
 */

data class User(val name: String, val age: Int, val city: String, val roles: Set<String> = setOf())

// Инфиксная функция для проверки возраста
infix fun User.ageGreaterThan(age: Int): Boolean = TODO()

// Инфиксная функция для проверки города
infix fun User.livesIn(city: String): Boolean = TODO()