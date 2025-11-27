package ru.tbank.education.school.lesson3.lection.constructor

class BankAccount(val owner: String, var balance: Double) {

    // блок инициализации
    init {
        println("Открыт счёт для $owner с балансом $balance руб.")
    }

    // дополнительный конструктор: счёт без начального баланса
    constructor(owner: String) : this(owner, 0.0) {
        println("Создан пустой счёт для $owner")
    }

    fun deposit(amount: Double) {
        balance += amount
        println("На счёт $owner зачислено $amount руб. Баланс: $balance")
    }

    fun withdraw(amount: Double) {
        if (amount <= balance) {
            balance -= amount
            println("Со счёта $owner снято $amount руб. Баланс: $balance")
        } else {
            println("Недостаточно средств на счёте $owner")
        }
    }
}

fun main() {
    // основной конструктор (с балансом)
    val account1 = BankAccount("Аня", 1000.0)
    account1.deposit(500.0)
    account1.withdraw(200.0)

    println("-----")

    // дополнительный конструктор (без баланса)
    val account2 = BankAccount("Петя")
    account2.deposit(300.0)
    account2.withdraw(500.0)
}

