package ru.tbank.education.school.lesson3.seminar

import ru.tbank.education.school.lesson3.seminar.bank.Bank
import ru.tbank.education.school.lesson3.seminar.bank.models.Currency
import ru.tbank.education.school.lesson3.seminar.bank.models.Customer

/*
 * 1. Зарегистрировать клиента и открыть ему кредитный и дебетовый счёт.
 * 2. Пополнить кредитный счёт (например, 3000).
 * 3. Снять часть денег с кредитного счёта (например, 1000).
 * 4. Перевести деньги с кредитного счёта на дебетовый счёт (например, 500).
 * 5. Вывести отчёты по обоим счетам и убедиться, что перевод отображается в истории.
 */

fun main() {
    val bank = Bank("Plata")
    val customer = bank.registerCustomer("Oleg")

    val debitCart = bank.openDebitAccount(customer, Currency.USD)
    val creditCard = bank.openCreditAccount(customer, Currency.USD, 1000)

    bank.deposit(debitCart, 1000, "За услуги")
    bank.withdraw(creditCard, 13121241, "На сваи")

    bank.transfer(creditCard, debitCart, 123, "На колодец")

    debitCart.printReport()
    creditCard.printReport()


}