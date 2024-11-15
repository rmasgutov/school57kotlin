package ru.tbank.education.school.lesson3.accounting

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CategoryTest {
    @Test
    fun findProductsTest() {
        // given
        val category = ElectronicsCategory("Electr", listOf(
            Product("Телевизор",10000,10),
            Product("Смартфон",10000,10)
        ).toMutableList()
        )
        // when
        val actual1 = category.findProducts("Телевизор")

        // then
        assertEquals(1, actual1.size)
        assertEquals(category.products[0], actual1[0])
        // when
        val actual2 = category.findProducts("Смартфон")

        // then
        assertEquals(1, actual2.size)
        assertEquals(category.products[1], actual2[0])
    }

    @Test
    fun findProductsAllInCategoryTest() {
        // given
        val category = ElectronicsCategory("Electr", listOf(
            Product("Телевизор",10000,10),
            Product("Смартфон",10000,10)
        ).toMutableList()
        )
        // when
        val actual1 = category.findProducts("Смартфон")
        val actual2 = category.findProducts("Телевизор")

        // then
        assertEquals(1, actual1.size)
        assertEquals(category.products[1], actual1[0])
        assertEquals(category.products[0], actual2[0])
    }
}
