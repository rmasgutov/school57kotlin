package ru.tbank.education.school.lesson7.lection.collections

data class OrderInfo(
    val id: Int,
    val user: String,
    val amount: Double,
    val isPaid: Boolean,
    val isDelivered: Boolean
)

fun main() {
    val orders = listOf(
        OrderInfo(id = 1, user = "Анна", amount = 1200.0, isPaid = true, isDelivered = false),
        OrderInfo(id = 2, user = "Борис", amount = 800.0, isPaid = true, isDelivered = false),
        OrderInfo(id = 3, user = "Виктор", amount = 2000.0, isPaid = true, isDelivered = true),
        OrderInfo(id = 4, user = "Ирина", amount = 1500.0, isPaid = false, isDelivered = false)
    )

    // Фильтруем только оплаченные, но не доставленные заказы с суммой > 1000
    val pendingOrders = orders
        .filter { it.isPaid }
        .filter { !it.isDelivered }
        .filter { it.amount > 1000 }
        .toList()

    println("Невыполненные заказы:")
    pendingOrders.forEach(::println)
}
