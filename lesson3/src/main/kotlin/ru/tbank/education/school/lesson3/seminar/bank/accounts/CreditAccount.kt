package ru.tbank.education.school.lesson3.seminar.bank.accounts

import ru.tbank.education.school.lesson3.seminar.bank.models.Currency
import ru.tbank.education.school.lesson3.seminar.bank.models.Customer
import ru.tbank.education.school.lesson3.seminar.bank.models.DepositTransaction
import ru.tbank.education.school.lesson3.seminar.bank.models.WithdrawalTransaction

class CreditAccount(
    id: String,
    owner: Customer,
    currency: Currency,
    private val creditLimit: Int
) : Account(id, owner, currency) {

    override fun deposit(amount: Int, description: String): Boolean {
        balance += amount
        record(DepositTransaction("T-${transactions.size + 1}", this, amount, description))
        return true
    }

    override fun withdraw(amount: Int, description: String): Boolean {
        if (balance - amount < -creditLimit) return false
        balance -= amount
        record(WithdrawalTransaction("T-${transactions.size + 1}", this, amount, description))
        return true
    }
}