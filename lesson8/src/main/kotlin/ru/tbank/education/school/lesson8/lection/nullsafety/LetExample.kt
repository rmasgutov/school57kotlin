package ru.tbank.education.school.lesson8.lection.nullsafety

fun main() {
    val order = Order(
        id = "A1",
        customer = Customer(
            name = "Alice",
            address = Address(
                city = City(name = "Amsterdam")
            )
        )
    )

    val resultLet = order.customer?.address?.city?.name?.let { name ->
        "City '$name' length = ${name.length}"
    } ?: "No city name"
    println(resultLet)
}
