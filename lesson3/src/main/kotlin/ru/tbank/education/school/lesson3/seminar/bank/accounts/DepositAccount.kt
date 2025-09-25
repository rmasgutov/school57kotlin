package ru.tbank.education.school.lesson3.fitness.club.accounts
import ru.tbank.education.school.lesson3.fitness.club.models.Currency
import ru.tbank.education.school.lesson3.fitness.club.models.Customer
import java.time.LocalDateTime

class DepositAccount {
    id: String,
    owner: Customer,
    currency; Currency,
    val closeDate: LocalDateTime
} : Account(id, owner, currency) {

    override fun deposit(amount: )
}