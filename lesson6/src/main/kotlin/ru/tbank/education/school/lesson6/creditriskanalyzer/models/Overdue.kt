package ru.tbank.education.school.lesson6.creditriskanalyzer.models

import java.util.UUID

data class Overdue(
    val loanId: UUID,
    val clientId: UUID,
    val daysOverdue: Int
)