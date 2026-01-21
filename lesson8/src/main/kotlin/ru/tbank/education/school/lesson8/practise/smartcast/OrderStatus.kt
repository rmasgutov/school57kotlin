package ru.tbank.education.school.lesson8.practise.smartcast

sealed class OrderStatus {
    object Pending : OrderStatus()
    data class Processing(val manager: String) : OrderStatus()
    data class Shipped(val trackingNumber: String) : OrderStatus()
    object Delivered : OrderStatus()
    data class Canceled(val reason: String) : OrderStatus()
}

/**
 * 1. Напишите функцию, которая обрабатывает статус заказа и возвращает строку:
 *     - Для Processing: "Заказ обрабатывает менеджер: [имя]"
 *     - Для Shipped: "Заказ отправлен. Трек-номер: [номер]"
 *     - Для Canceled: "Заказ отменен. Причина: [причина]"
 *     - Для других статусов: соответствующие сообщения
 *
 * 2. Используйте when с проверкой типов для smart cast
 */

fun handleOrderStatus(status: OrderStatus): String {
    return when (status) {
        is OrderStatus.Processing -> "Заказ обрабатывает менеджер:${status.manager}"
        is OrderStatus.Shipped -> "Заказ отправлен. Трек-номер: ${status.trackingNumber}"
        is OrderStatus.Canceled -> "Заказ отменен. Причина: ${status.reason}"
        else -> "Unknown type"
    }
}
