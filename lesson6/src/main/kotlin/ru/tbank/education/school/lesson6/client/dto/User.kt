package ru.tbank.education.school.lesson6.client.dto

data class User(
    val id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val phone: String,
    val userStatus: Int
)