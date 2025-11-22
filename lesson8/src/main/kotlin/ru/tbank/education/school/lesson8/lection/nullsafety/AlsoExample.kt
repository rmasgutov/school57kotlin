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

    val lenWithLog = order.customer?.address?.city?.name
        ?.also { println("We have city name: $it") }
        ?.length ?: -1

    println("lenWithLog=$lenWithLog")
}
