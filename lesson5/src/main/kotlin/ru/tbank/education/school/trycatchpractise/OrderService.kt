package ru.tbank.education.school.trycatchpractise

/**
 * Интерфейс для управления заказами в магазине.
 */
interface SaveOrderService {

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


data class OrderItem(val productId: String, val quantity: Int)


class OrderService() : SaveOrderService {

    val cart = mutableListOf<OrderItem>()
    override fun addToCart(productId: String, quantity: Int) {
        if (quantity <= 0) throw IllegalArgumentException()
        cart.add(OrderItem(productId, quantity))
    }

    override fun removeFromCart(productId: String) {
        if (!cart.removeIf { it.productId == productId }) {
            throw IllegalArgumentException()
        }
    }
}