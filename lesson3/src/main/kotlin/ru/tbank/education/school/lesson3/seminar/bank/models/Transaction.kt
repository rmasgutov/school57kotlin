package ru.tbank.education.school.lesson3.seminar.bank.models

import ru.tbank.education.school.lesson3.seminar.bank.accounts.Account

sealed class Transaction(
    val id: String,
    val amount: Int,
    val description: String
)

class DepositTransaction(id: String, val to: Account, amount: Int, description: String)
    : Transaction(id, amount, description)

class WithdrawalTransaction(id: String, val from: Account, amount: Int, description: String)
    : Transaction(id, amount, description)

class TransferTransaction(id: String, val from: Account, val to: Account, amount: Int, description: String)
    : Transaction(id, amount, description)