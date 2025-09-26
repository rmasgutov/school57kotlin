package ru.tbank.education.school.lesson3.seminar.bank

import ru.tbank.education.school.lesson3.seminar.bank.accounts.Account
import ru.tbank.education.school.lesson3.seminar.bank.accounts.CreditAccount
import ru.tbank.education.school.lesson3.seminar.bank.accounts.DebitAccount
import ru.tbank.education.school.lesson3.seminar.bank.models.Currency
import ru.tbank.education.school.lesson3.seminar.bank.models.Customer
import java.time.LocalDate

class Bank(val name: String) {
    private val customers = mutableListOf<Customer>()
    private val accounts = mutableListOf<Account>()

    private var customerSeq = 1
    private var accountSeq = 1

    fun registerCustomer(fullName: String): Customer {
        val c = Customer("C-${customerSeq++}", fullName)
        customers += c
        return c
    }

    fun openDebitAccount(customer: Customer, currency: Currency) =
        DebitAccount("A-${accountSeq++}", customer, currency).also { accounts += it }

    fun openCreditAccount(customer: Customer, currency: Currency, limit: Int) =
        CreditAccount("A-${accountSeq++}", customer, currency, limit).also { accounts += it }


    fun deposit(to: Account, amount: Int, description: String) {
        to.deposit(amount, description)
    }

    fun withdraw(from: Account, amount: Int, description: String) {
        from.withdraw(amount, description)
    }

    fun transfer(from: Account, to: Account, amount: Int, description: String) {
        if (!from.transfer(to, amount, description)) {
            println("Перевод не выполнен: недостаточно средств или лимит исчерпан")
        }
    }
}
