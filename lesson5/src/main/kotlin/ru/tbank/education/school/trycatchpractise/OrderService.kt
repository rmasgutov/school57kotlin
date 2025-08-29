package ru.tbank.education.school.trycatchpractise

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