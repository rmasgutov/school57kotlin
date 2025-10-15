package ru.tbank.education.school
interface KitchenDisplay {
    fun showOrder(orderId: Int, items: List<String>)
    fun updateStatus(orderId: Int, status: String)
}
class KitchenScreen : KitchenDisplay {
    override fun showOrder(orderId: Int, items: List<String>) {
        println("Заказ #$orderId: ${items.joinToString()}")
    }
    override fun updateStatus(orderId: Int, status: String) {
        println("Заказ #$orderId: $status")
    }
}
