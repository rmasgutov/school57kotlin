package ru.tbank.education.school.lesson8.practise

object EmailValidator {

    private val emailRegex =
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    fun validateEmail(email: String): Boolean {
        if (email.isBlank()) return false
        return emailRegex.matches(email)
    }
}
