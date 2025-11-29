package ru.tbank.education.school.lesson6.creditriskanalyzer.models

import java.time.LocalDateTime
import java.util.UUID

data class Loan(
    val id: UUID,
    val clientId: UUID,
    val debt: Long,
    val isClosed: Boolean,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime? = null,
    val currency: Currency
)