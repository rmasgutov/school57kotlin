package ru.tbank.education.school.lesson8.practise.smartcast

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OrderStatusTests {

    @Test
    fun `handleOrderStatus returns correct message for Pending`() {
        val result = handleOrderStatus(OrderStatus.Pending)
        assertEquals("Заказ ожидает обработки", result)
    }

    @Test
    fun `handleOrderStatus returns correct message for Processing`() {
        val status = OrderStatus.Processing("Иван Петров")
        val result = handleOrderStatus(status)
        assertEquals("Заказ обрабатывает менеджер: Иван Петров", result)
    }

    @Test
    fun `handleOrderStatus returns correct message for Shipped`() {
        val status = OrderStatus.Shipped("TRACK-999")
        val result = handleOrderStatus(status)
        assertEquals("Заказ отправлен. Трек-номер: TRACK-999", result)
    }

    @Test
    fun `handleOrderStatus returns correct message for Delivered`() {
        val result = handleOrderStatus(OrderStatus.Delivered)
        assertEquals("Заказ доставлен", result)
    }

    @Test
    fun `handleOrderStatus returns correct message for Canceled`() {
        val status = OrderStatus.Canceled("Нет товара на складе")
        val result = handleOrderStatus(status)
        assertEquals("Заказ отменен. Причина: Нет товара на складе", result)
    }
}
