package ru.tbank.education.school.lesson7.lection.collections

data class Order(val id: Int, val product: String)
data class User(val name: String, val orders: List<Order>)

fun main() {
    val users = listOf(
        User(
            name = "Анна",
            orders = listOf(
                Order(id = 1, product = "Телефон"),
                Order(id = 2, product = "Чехол")
            )
        ),
        User(
            name = "Борис",
            orders = listOf(
                Order(id = 3, product = "Ноутбук")
            )
        ),
        User(
            name = "Виктор",
            orders = listOf(
                Order(id = 4, product = "Книга"),
                Order(id = 5, product = "Рюкзак")
            )
        )
    )

    // Соберем все заказы всех пользователей в один список
    val allOrders = users.flatMap { it.orders }

    println(allOrders)
}
