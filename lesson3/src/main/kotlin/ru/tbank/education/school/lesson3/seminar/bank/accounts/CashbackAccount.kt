//package ru.tbank.education.school.lesson3.seminar.bank.accounts
//
//import ru.tbank.education.school.lesson3.seminar.bank.models.Currency
//import ru.tbank.education.school.lesson3.seminar.bank.models.Customer
//import ru.tbank.education.school.lesson3.seminar.bank.models.DepositTransaction
//import javax.management.Descriptor
//
//class CashbackAccount (
//    id: String,
//    owner: Customer,
//    currency: Currency
//) : Account(id, owner, currency) {
//    override fun deposit(amount: Int, descriptor: String): Boolean {
//        balance -= amount
//        record(DepositTransaction("Т-${transactions.size + 1}", this, amount, descriptor))
//    }
//}
