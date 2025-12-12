package ru.tbank.education.school.lesson7.practise.task1

/**
 * Задание: Разверни заказы в списки позиций.
 *
 * Дано: список Order, у каждого есть список OrderItem (productId, quantity, pricePerUnit).
 * Нужно: получить ПЛОСКИЙ список LineItem с вычисленной суммой (quantity * pricePerUnit),
 * а также добавить поле orderId и итог с налогом TAX 20% (totalWithTax).
 *
 * Подсказки:
 *  - Используй flatMap для разворачивания нескольких списков items в один список.
 *  - На каждом элементе посчитай total = quantity * pricePerUnit и totalWithTax = total * 1.2.
 */
data class Order(val id: String, val items: List<OrderItem>)
data class OrderItem(val productId: String, val quantity: Int, val pricePerUnit: Double)
data class LineItem(val orderId: String, val productId: String, val quantity: Int, val total: Double, val totalWithTax: Double)

fun expandOrders(orders: List<Order>, tax: Double = 0.20): List<LineItem> {
    TODO()
}