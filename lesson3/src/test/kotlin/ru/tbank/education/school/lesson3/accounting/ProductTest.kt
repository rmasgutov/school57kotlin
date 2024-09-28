package ru.tbank.education.school.lesson3.accounting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProductTest {
    @Test
    fun plusTest() {
        // given
        val product1 = Product(name = "Телевизор", price = 10000.0, count = 10)
        val product2 = Product(name = "Телевизор", price = 10000.0, count = 10)

        // when
        val actual = product1 + product2

        // then
        assertEquals(20, actual.count)
        assertEquals(10000.0, actual.price)
        assertEquals("Телевизор", actual.name)

        assertEquals(10, product1.count)
        assertEquals(10, product2.count)
    }

    @Test
    fun plusErrorTest() {
        // given
        val product1 = Product(name = "Телевизор", price = 10000.0, count = 10)
        val product2 = Product(name = "Смартфон", price = 10000.0, count = 10)

        // when
        assertThrows<IllegalArgumentException> { product1 + product2 }

        // then
        assertEquals(10, product1.count)
        assertEquals(10, product2.count)
    }

    @Test
    fun equalsAndHashCodeTest() {
        // given
        val product1 = Product(name = "Телевизор", price = 10000.0, count = 10)
        val product2 = product1.copy(price = 500.0)
        val product3 = product1.copy(count = 5)
        val product4 = product1.copy(name = "Смартфон")

        // expect
        assert(product1 == product2)
        assert(product1 == product3)
        assert(product1 != product4)
    }
}
