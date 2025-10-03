package ru.tbank.education.school.lesson3.club.dataclasses

// Иммутабельная модель транзакции
data class Transaction(
    val id: String,
    val fromAccount: String,
    val toAccount: String,
    val amount: Double
)


fun main() {

}