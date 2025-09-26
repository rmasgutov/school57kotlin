package ru.tbank.education.school.lesson3.seminar

import ru.tbank.education.school.lesson3.seminar.bank.Bank
import ru.tbank.education.school.lesson3.seminar.bank.models.Currency

fun main() {
    val Bank = Bank("Z-Bank")
    val user = Bank.registerCustomer("Empty")
    val DebAccount = Bank.openDebitAccount(user, Currency.USD)
    val CreditAccount = Bank.openCreditAccount(user, Currency.RUB, 10000)
    Bank.deposit(DebAccount, 1000, "TopUp")
    Bank.transfer(DebAccount,CreditAccount, 1000, "Transfer")
    CreditAccount.printReport()
    DebAccount.printReport()






}