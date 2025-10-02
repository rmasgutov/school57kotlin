package ru.tbank.education.school.lesson4.Homewor1.models

import interfaces.UserProfile

abstract class User(
    protected val id: Int,
    private val email: String,
    val username: String
) : UserProfile {
    abstract override fun getProfile(): String
}