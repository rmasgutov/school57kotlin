package ru.tbank.education.school.lesson3.accounting

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CategoryTest {
    @Test
    fun findProductsTest() {
        // given
        val category = ElectronicsCategory(products = listOf(
            Product(name = "Телевизор", price = 10000.0, count = 10),
            Product(name = "Смартфон", price = 10000.0, count = 10)
        ))
        // when
        val actual1 = category.findProducts(request = "теле")

        // then
        assertEquals(1, actual1.size)
        assertEquals(category.products[0], actual1[0])
        // when
        val actual2 = category.findProducts(request = "смарт")

        // then
        assertEquals(1, actual2.size)
        assertEquals(category.products[1], actual2[0])
    }

    @Test
    fun findProductsAllInCategoryTest() {
        // given
        val category = ElectronicsCategory(products = listOf(
            Product(name = "Телевизор", price = 10000.0, count = 10),
            Product(name = "Смартфон", price = 10000.0, count = 10)
        ))
        // when
        val actual1 = category.findProducts(request = "эле")

        // then
        assertEquals(2, actual1.size)
        assertEquals(category.products[0], actual1[0])
        assertEquals(category.products[1], actual1[1])
    }
}
