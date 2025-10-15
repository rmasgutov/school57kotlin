package ru.tbank.education.school.lesson6.creditriskanalyzer.models

import java.util.UUID

enum class TicketTopic {
    PAYMENT_ERROR, CARD_BLOCKED, LOAN_REQUEST, FRAUD_ALERT, OTHER
}

data class Ticket(
    val clientId: UUID,
    val topic: TicketTopic,
    val resolved: Boolean
)