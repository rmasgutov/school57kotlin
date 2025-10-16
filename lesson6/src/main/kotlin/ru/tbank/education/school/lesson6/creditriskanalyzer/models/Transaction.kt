package ru.tbank.education.school.lesson6.creditriskanalyzer.models

import java.time.LocalDateTime
import java.util.UUID

enum class TransactionCategory {
    SHOPPING, RENT, FOOD, SALARY, CRYPTO, GAMBLING, TRANSFER, OTHER
}

data class Transaction(
    val id: UUID,
    val clientId: UUID,
    val accountId: UUID,
    val amount: Long,
    val category: TransactionCategory,
    val date: LocalDateTime,
    val currency: Currency
)