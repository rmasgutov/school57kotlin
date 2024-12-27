package ru.tbank.education.school.lesson7
import java.time.LocalDate
fun main() {
    val deposit = Deposit(1234566789, 10000, LocalDate.parse("2024-01-01"), 7, 20.0, 1)
    println(currentBalance(deposit))
    //test
}