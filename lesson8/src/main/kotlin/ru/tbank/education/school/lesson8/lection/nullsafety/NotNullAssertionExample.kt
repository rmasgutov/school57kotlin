package ru.tbank.education.school.lesson8.lection.nullsafety

fun main() {
    val order = Order(
        id = "B2",
        customer = Customer(
            name = "Bob",
            address = null // адрес неизвестен
        )
    )

    try {
        // Здесь у order2.customer?.address == null -> !! кинет NPE
        val forcedLen = order.customer!!.address!!.city!!.name!!.length
        println("forcedLen=$forcedLen")
    } catch (e: NullPointerException) {
        println("forcedLen crashed with NPE: ${e.message}")
    }
}
