package ru.tbank.education.school.lesson3.seminar.dataclasses

// Иммутабельная модель транзакции
data class Transaction(
    val id: String,
    val fromAccount: String,
    val toAccount: String,
    val amount: Double
)


fun main() {
    val tx1 = Transaction("TX-1", "ACC-1", "ACC-2", 100.0)

    println(tx1)

    val tx2 = tx1.copy(amount = 200.0)

    println(tx2)

    println(tx1 == tx2)
}