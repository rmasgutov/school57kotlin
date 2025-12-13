package ru.tbank.education.school.lesson10.homework

// Для класса
@Target(AnnotationTarget.CLASS)
annotation class DocClass(
    val description: String,
    val author: String = "Unknown",
    val version: String = "1.0"
)

// Для свойств
@Target(AnnotationTarget.PROPERTY)
annotation class DocProperty(
    val description: String,
    val example: String = ""
)

// Для методов
@Target(AnnotationTarget.FUNCTION)
annotation class DocMethod(
    val description: String,
    val returns: String = "No description"
)

// Для параметров методов
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class DocParam(
    val description: String
)

// Для скрытия из документации
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
annotation class InternalApi