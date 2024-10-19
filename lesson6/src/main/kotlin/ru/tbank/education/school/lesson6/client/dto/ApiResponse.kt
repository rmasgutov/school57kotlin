package ru.tbank.education.school.lesson6.client.dto

data class ApiResponse(
    val code: Int,
    val type: String?,
    val message: String?
)
