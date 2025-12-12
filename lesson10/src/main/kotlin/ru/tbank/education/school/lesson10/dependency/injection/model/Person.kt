package ru.tbank.education.school.lesson10.dependency.injection.model

/**
 * Модель данных для представления пользователя.
 * В Kotlin data class автоматически генерирует equals, hashCode, toString и copy.
 */
data class Person(val name: String, val email: String)
