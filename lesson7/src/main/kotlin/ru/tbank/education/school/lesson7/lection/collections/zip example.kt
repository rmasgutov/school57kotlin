package ru.tbank.education.school.lesson7.lection.collections

fun main() {
    val months = listOf("Янв", "Фев", "Мар", "Апр", "Май")
    val revenue = listOf(1000, 1200, 800, 1400, 1300)

    val report = months
        .zip(revenue) { month, amount -> "$month — прибыль ${amount}₽" }

    println("Финансовый отчёт:")
    report.forEach(::println)
}