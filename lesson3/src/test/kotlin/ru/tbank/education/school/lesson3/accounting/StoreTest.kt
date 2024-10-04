package ru.tbank.education.school.lesson3.accounting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StoreTest {

    @Test
    fun sellFoodSuccessTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory(mutableListOf(Product(name = "Хлеб", price = 10.0, count = 10))))
        )
        val sale = Product(name = "Хлеб", price = 10.0, count = 4)

        // when
        Store.sell(sale)

        // then
        assertEquals(1, Store.sales.size)
        assertEquals(sale, Store.sales[0])
        assertEquals(2, Store.warehouse[0].products[0].count)
    }
    @Test
    fun sellElectronicsSuccessTest() {
        // given
        Store.init(
            warehouse = listOf(ElectronicsCategory(mutableListOf(Product(name = "Смартфон", price = 10.0, count = 10))))
        )
        val sale = Product(name = "Смартфон", price = 10.0, count = 4)

        // when
        Store.sell(sale)

        // then
        assertEquals(1, Store.sales.size)
        assertEquals(sale, Store.sales[0])
        assertEquals(9, Store.warehouse[0].products[0].count)
    }

    @Test
    fun sellLackingTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory(mutableListOf(Product(name = "Хлеб", price = 10.0, count = 10))))
        )
        val sale = Product(name = "Хлеб", price = 10.0, count = 20)

        // when
        assertThrows<IllegalArgumentException> {
            Store.sell(sale)
        }

        // then
        assertEquals(0, Store.sales.size)
        assertEquals(10, Store.warehouse[0].products[0].count)
    }

    @Test
    fun sellNotFoundTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory(mutableListOf(Product(name = "Хлеб", price = 10.0, count = 10))))
        )
        val sale = Product(name = "Яблоко", price = 10.0, count = 20)

        // when
        assertThrows<IllegalArgumentException> {
            Store.sell(sale)
        }

        // then
        assertEquals(0, Store.sales.size)
        assertEquals(10, Store.warehouse[0].products[0].count)
    }

    @Test
    fun searchInCategoryTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory(mutableListOf(Product(name = "Хлеб", price = 10.0, count = 10))))
        )

        // when
        val actual = Store.search("ле")

        // then
        assertEquals(1, actual.size)
        assertEquals("Хлеб", actual[0].name)
    }

    @Test
    fun searchInCategoryCaseInsensitiveTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory(mutableListOf(Product(name = "Хлеб", price = 10.0, count = 10))))
        )

        // when
        val actual = Store.search("хле")

        // then
        assertEquals(1, actual.size)
        assertEquals("Хлеб", actual[0].name)
    }

    @Test
    fun searchAllCategoryTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory(mutableListOf(Product(name = "Хлеб", price = 10.0, count = 10))))
        )

        // when
        val actual = Store.search("Прод")

        // then
        assertEquals(1, actual.size)
        assertEquals("Хлеб", actual[0].name)
    }

    @Test
    fun searchAllCategoryCaseInsensitiveTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory(mutableListOf(Product(name = "Хлеб", price = 10.0, count = 10))))
        )

        // when
        val actual = Store.search("прод")

        // then
        assertEquals(1, actual.size)
        assertEquals("Хлеб", actual[0].name)
    }

    @Test
    fun topUpTest() {
        // given поставка в магазин
        Store.init(
            warehouse = mutableListOf(
                ElectronicsCategory(
                    listOf(
                        Product(name = "Телевизор", price = 10000.0, count = 10),
                        Product(name = "Смартфон", price = 20000.0, count = 30),
                    )
                )
            )
        )
        val shipping = listOf(
            ElectronicsCategory(
                listOf(
                    Product(name = "Телевизор", count = 10),
                    Product(name = "Смартфон", count = 10),
                )
            )
        )
        // when
        Store.topUp(shipping)

        // then
        val actualTvCount = Store
            .warehouse.first { it is ElectronicsCategory }
            .products.first { it.name == "Телевизор" }
            .count
        assertEquals(20, actualTvCount)
        val actualPhoneCount = Store
            .warehouse.first { it is ElectronicsCategory }
            .products.first { it.name == "Смартфон" }
            .count
        assertEquals(40, actualPhoneCount)
    }
}
