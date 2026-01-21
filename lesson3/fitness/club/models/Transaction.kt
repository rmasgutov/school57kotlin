package ru.tbank.education.school.lesson3.club.models

import ru.tbank.education.school.lesson3.club.accounts.Account

sealed class Transaction(
    val id: String,
    val amount: Int,
    val description: String
)

class Transaction(id: String, val to: Account, amount: Int, description: String)
    : Transaction(id, amount, description)