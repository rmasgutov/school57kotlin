package ru.tbank.education.school.lesson7.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task1.Order
import ru.tbank.education.school.lesson7.practise.task1.OrderItem
import ru.tbank.education.school.lesson7.practise.task1.expandOrders

class ExpandOrdersTest {

    @Test
    fun `expands orders into line items with tax`() {
        val orders = listOf(
            Order(
                id = "order-1",
                items = listOf(
                    OrderItem("A", 2, 10.0),
                    OrderItem("B", 1, 5.0)
                )
            ),
            Order(
                id = "order-2",
                items = listOf(
                    OrderItem("C", 3, 20.0)
                )
            )
        )

        val result = expandOrders(orders)

        assertEquals(3, result.size)

        val first = result.first { it.productId == "A" }
        assertEquals("order-1", first.orderId)
        assertEquals(2, first.quantity)
        assertEquals(20.0, first.total, 0.001)
        assertEquals(24.0, first.totalWithTax, 0.001) // 20 * 1.2

        val third = result.first { it.productId == "C" }
        assertEquals(60.0, third.total, 0.001)
        assertEquals(72.0, third.totalWithTax, 0.001)
    }

    @Test
    fun `handles empty orders list`() {
        val result = expandOrders(emptyList())
        assertTrue(result.isEmpty())
    }

    @Test
    fun `handles order with no items`() {
        val orders = listOf(Order("empty", emptyList()))
        val result = expandOrders(orders)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `applies custom tax rate`() {
        val orders = listOf(
            Order("o1", listOf(OrderItem("P", 1, 100.0)))
        )

        val result = expandOrders(orders, tax = 0.10)

        assertEquals(1, result.size)
        val item = result[0]
        assertEquals(100.0, item.total, 0.001)
        assertEquals(110.0, item.totalWithTax, 0.001)
    }
}
