package ru.tbank.education.school.lesson6.creditriskanalyzer.models

import java.util.UUID

data class Account(
    val id: UUID,
    val clientId: UUID,
    val balance: Long,
    val type: AccountType,
    val currency: Currency
)

enum class AccountType {
    DEBIT, SAVINGS, CREDIT
}