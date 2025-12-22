package ru.tbank.education.school.lesson8.practise.smartcast

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class ProductTests {

    // ---- Tests for getMostExpensivePrice ----

    @Test
    fun `getMostExpensivePrice returns max price`() {
        val products = listOf(
            Product(1, "A", 10.0, "Tech"),
            Product(2, "B", 30.5, "Tech"),
            Product(3, "C", 20.0, "Food")
        )

        val result = getMostExpensivePrice(products)
        assertEquals(30.5, result)
    }

    @Test
    fun `getMostExpensivePrice ignores null prices`() {
        val products = listOf(
            Product(1, "A", null, "Tech"),
            Product(2, "B", 50.0, "Tech"),
            Product(3, "C", null, "Food")
        )

        val result = getMostExpensivePrice(products)
        assertEquals(50.0, result)
    }

    @Test
    fun `getMostExpensivePrice returns null when all prices null`() {
        val products = listOf(
            Product(1, "A", null, "Tech"),
            Product(2, "B", null, "Tech")
        )

        val result = getMostExpensivePrice(products)
        assertNull(result)
    }

    @Test
    fun `getMostExpensivePrice returns null when list empty`() {
        val result = getMostExpensivePrice(emptyList())
        assertNull(result)
    }

    // ---- Tests for groupProductsByCategory ----

    @Test
    fun `groupProductsByCategory groups by categories`() {
        val products = listOf(
            Product(1, "A", 10.0, "Tech"),
            Product(2, "B", 20.0, "Tech"),
            Product(3, "C", 5.0, "Food")
        )

        val result = groupProductsByCategory(products)

        assertEquals(2, result["Tech"]?.size)
        assertEquals(1, result["Food"]?.size)
    }

    @Test
    fun `groupProductsByCategory puts null category into Без категории`() {
        val products = listOf(
            Product(1, "A", 10.0, null),
            Product(2, "B", 20.0, "Tech"),
            Product(3, "C", 5.0, null)
        )

        val result = groupProductsByCategory(products)

        assertEquals(2, result["Без категории"]?.size)
        assertEquals(1, result["Tech"]?.size)
    }

    @Test
    fun `groupProductsByCategory returns empty map when list empty`() {
        val result = groupProductsByCategory(emptyList())
        assertTrue(result.isEmpty())
    }
}
