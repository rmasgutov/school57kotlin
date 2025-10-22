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
    val bank = Bank(name = "Kotlin банк")

    val customer = bank.registerCustomer(fullName = "Никита")

    val debitCard = bank.openDebitAccount(customer, Currency.RUB)
    val creditCard = bank.openCreditAccount(customer, Currency.RUB, limit=1000)

    bank.deposit(to = debitCard, amount = 3000, decription = "Зарплата")
    bank.withdraw(from = creditCard, amount = 500, decription = "На машину")

    bank.transfer(from = creditCard, to = debitCard, amount = 500, descriptoin = "На машину")

    debitCard.printReport()
    creditCard.printReport()


}