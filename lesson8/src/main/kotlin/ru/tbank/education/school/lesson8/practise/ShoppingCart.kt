package ru.tbank.education.school.lesson8.practise

import java.time.LocalDateTime

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val category: String,
    val weight: Double
)

data class CartItem(
    val product: Product,
    val quantity: Int,
    val addedAt: LocalDateTime = LocalDateTime.now()
)

data class Discount(
    val value: Double,
    val minQuantity: Int = 1,
    val applicableCategories: Set<String> = emptySet()
)

class ShoppingCart {
    private val items: MutableList<CartItem> = mutableListOf()
    private var discounts: MutableList<Discount> = mutableListOf()

    fun addProduct(product: Product, quantity: Int): Boolean {
        TODO("Добавить товар в корзину")
    }
    
    fun removeProduct(productId: String, quantity: Int): Boolean {
        TODO("Удалить товар из корзины")
    }
    
    fun updateQuantity(productId: String, newQuantity: Int): Boolean {
        TODO("Изменить количество товара")
    }
    
    fun clear() {
        TODO("Очистить корзину")
    }

    fun getSubtotal(): Double {
        TODO("Рассчитать сумму без скидок")
    }
    
    fun getTotalWeight(): Double {
        TODO("Рассчитать общий вес заказа")
    }
    
    fun getTotalWithDiscounts(): Double {
        TODO("Рассчитать итоговую сумму со скидками")
    }
    
    fun applyDiscount(discount: Discount) {
        TODO("Применить скидку к корзине")
    }

    
    fun validateWeightLimit(maxWeight: Double = 50.0): Boolean {
        TODO("Проверить не превышен ли лимит веса")
    }

    fun getMostExpensiveItem(): CartItem? {
        TODO("Найти самый дорогой товар")
    }
    
    fun getItemsByCategory(category: String): List<CartItem> {
        TODO("Получить товары по категории")
    }

    fun getItemCount(): Int {
        TODO("Получить общее количество товаров")
    }
    
    fun getUniqueProductsCount(): Int {
        TODO("Получить количество уникальных товаров")
    }
}