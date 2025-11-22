package ru.tbank.education.school.lesson8.lection.nullsafety

fun main() {
    val order1 = Order(
        id = "A1",
        customer = Customer(
            name = "Alice",
            address = Address(
                city = City(name = "Amsterdam")
            )
        )
    )

    val order2 = Order(
        id = "B2",
        customer = Customer(
            name = "Bob",
            address = null
        )
    )

    val len1 = order1.customer?.address?.city?.name?.length ?: 0
    val len2 = order2.customer?.address?.city?.name?.length ?: 0
    println("len1=$len1, len2=$len2")
}
