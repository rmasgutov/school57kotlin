package ru.tbank.education.school.lesson6.client.dto

data class User(
    val id: Long,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String,
    val userStatus: Int
)