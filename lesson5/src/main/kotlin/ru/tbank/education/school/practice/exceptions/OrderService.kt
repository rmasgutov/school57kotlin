package ru.tbank.education.school.practice.exceptions

/**
 * Интерфейс для управления заказами в магазине.
 */
interface OrderService {

    /**
     * Добавляет товар в корзину.
     *
     * @param productId ID товара
     * @param quantity количество
     * @throws IllegalArgumentException если quantity <= 0
     */
    fun addToCart(productId: String, quantity: Int)

    /**
     * Удаляет товар из корзины.
     *
     * @param productId ID товара
     * @throws NoSuchElementException если товара нет в корзине
     */
    fun removeFromCart(productId: String)

}

data class OrderItem(
    val productId: String,
    val quantity: Int
)

class OrderServiceImpl : OrderService {
    val cart = mutableListOf<OrderItem>()

    override fun addToCart(productId: String, quantity: Int) {
        if (quantity <= 0) {
            throw IllegalArgumentException(
                "Попытка добавить продукт в количестве меньшем, чем единица"
            )
        }

        val orderItem = OrderItem(productId, quantity)
        cart.add(orderItem)
    }

    override fun removeFromCart(productId: String) {
        if (!cart.removeIf { it.productId == productId }) {
            throw NoSuchElementException("Не найдены элементы для удаления из заказа")
        }
    }
}

//fun main() {
//    val orderServiceImpl = OrderServiceImpl()
//    orderServiceImpl.addToCart("123", 1)
//    orderServiceImpl.removeFromCart("123")
//    orderServiceImpl.removeFromCart("123")
//}