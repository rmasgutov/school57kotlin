package ru.tbank.education.school.lesson3.seminar

import ru.tbank.education.school.lesson3.seminar.bank.Bank
import ru.tbank.education.school.lesson3.seminar.bank.models.Currency

/*
 * 1. Зарегистрировать клиента и открыть ему кредитный и дебетовый счёт.
 * 2. Пополнить кредитный счёт (например, 3000).
 * 3. Снять часть денег с кредитного счёта (например, 1000).
 * 4. Перевести деньги с кредитного счёта на дебетовый счёт (например, 500).
 * 5. Вывести отчёты по обоим счетам и убедиться, что перевод отображается в истории.
 */

fun main() {
    val bank = Bank("Beta")

    val customer = bank.registerCustomer("ZXC")

    val debit = bank.openCreditAccount(customer, Currency.USD, 1000000)
    val credit = bank.openDebitAccount(customer, Currency.RUB)

    bank.deposit(debit, 5000, "")
    bank.withdraw(credit, 100000, "на додеп")
    bank.transfer(credit, debit, 1000, "Погашение")

    debit.printReport()
    credit.printReport()
}