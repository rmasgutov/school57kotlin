package ru.tbank.education.school
interface Order {
    val id: Int
    val items: List<MenuItem>
    val totalPrice: Double
    fun addItem(item: MenuItem)
}
fun interface OrderNotifier {
    @FunctionalInterface
    fun notify(orderId: String, message: String)
}
interface OnlineOrder : Order {
    val address: String
    fun calculateDeliveryTime(distance: Double, averageSpeed: Double): Double
}
class OnlineOrder(
    override val id: Int,
    override val address: String) : OnlineOrder {
    private val _items: MutableList<MenuItem> = mutableListOf()
    
    override val items: List<MenuItem>
        get() = items.toList()
    
    override val totalPrice: Double
        get() = items.sumOf { it.price }
    
    override fun addItem(item: MenuItem) {
        items.add(item)
        println("Добавлено: ${item.name} - ${item.price} руб.")
    }
    override fun calculateDeliveryTime(distance: Double, averageSpeed: Double): Double {
        return (distance / averageSpeed) * 60 + 15
    }
}
