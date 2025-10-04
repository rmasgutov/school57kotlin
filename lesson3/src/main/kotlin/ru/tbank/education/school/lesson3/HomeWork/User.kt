package ru.tbank.education.school.lesson3.HomeWork

abstract class User(
    protected val id: Int,
    private val email: String,
    val username: String
) {
    abstract fun Profile(): String
}
