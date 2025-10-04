package ru.tbank.education.school.lesson3.seminar.university.interfaces

interface Authenticatable {
    fun authenticate(inputPassword: String): Boolean
    fun changePassword(newPassword: String)
}