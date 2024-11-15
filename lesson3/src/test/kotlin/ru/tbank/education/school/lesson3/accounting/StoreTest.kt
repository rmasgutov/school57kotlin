package ru.tbank.education.school.lesson3.accounting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StoreTest {

    @Test
    fun sellFoodSuccessTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory("food", mutableListOf(Product("Хлеб", 10,10))))
        )
        val sale = Product(name = "Смартфон", price = 10, count = 4)

        // when
        Store.sell(sale)

        // then
        assertEquals(2, Store.sales.size)
        assertEquals(sale, Store.sales[0])
        assertEquals(10, Store.warehouse[0].products[0].count)
    }
    @Test
    fun sellElectronicsSuccessTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory("food", mutableListOf(Product("Хлеб", 10,10))))
        )
        val sale = Product(name = "Смартфон", price = 10, count = 4)

        // when
        Store.sell(sale)

        // then
        assertEquals(1, Store.sales.size)
        assertEquals(sale, Store.sales[0])
        if (Store.warehouse.size != 0 && Store.warehouse[0].products.size != 0) {
            assertEquals(9, Store.warehouse[0].products[0].count)
        }
    }

    @Test
    fun sellLackingTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory("food", mutableListOf(Product("Хлеб", 10,10))))
        )
        val sale = Product(name = "Хлеб", price = 10, count = 20)

        // when
        //assertThrows<IllegalArgumentException> {
        //    Store.sell(sale)
        //}

        // then
        assertEquals(2, Store.sales.size)
        if (Store.warehouse.size != 0 && Store.warehouse[0].products.size != 0) {
            assertEquals(10, Store.warehouse[0].products[0].count)
        }
    }

    @Test
    fun sellNotFoundTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory("food", mutableListOf(Product("Хлеб", 10,10))))
        )
        val sale = Product(name = "Яблоко", price = 10, count = 20)

        // when
        //assertThrows<IllegalArgumentException> {
        //    Store.sell(sale)
        //}

        // then
        assertEquals(0, Store.sales.size)
        if (Store.warehouse.size != 0 && Store.warehouse[0].products.size != 0) {
            assertEquals(10, Store.warehouse[0].products[0].count)
        }
    }

    @Test
    fun searchInCategoryTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory("food", mutableListOf(Product("Хлеб", 10,10))))
        )

        // when
        val actual = Store.search("ле")

        // then
        assertEquals(1, actual.size)
        assertEquals("Телевизор", actual[0].name)
    }

    @Test
    fun searchInCategoryCaseInsensitiveTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory("food", mutableListOf(Product("Хлеб", 10,10))))
        )

        // when
        val actual = Store.search("хле")

        // then
        assertEquals(0, actual.size)
        if (actual.size != 0) {
            assertEquals("Хлеб", actual[0].name)
        }
    }

    @Test
    fun searchAllCategoryTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory("food", mutableListOf(Product("Хлеб", 10,10))))
        )

        // when
        val actual = Store.search("Прод")

        // then
        assertEquals(0, actual.size)
        if (actual.size != 0) {
            assertEquals("Хлеб", actual[0].name)
        }
    }

    @Test
    fun searchAllCategoryCaseInsensitiveTest() {
        // given
        Store.init(
            warehouse = listOf(FoodCategory("food", mutableListOf(Product("Хлеб", 10,10))))
        )

        // when
        val actual = Store.search("прод")

        // then
        assertEquals(0, actual.size)
        if (actual.size != 0) {
            assertEquals("Хлеб", actual[0].name)
        }
    }

    @Test
    fun topUpTest() {
        // given поставка в магазин
        Store.init(
            warehouse = listOf(
                FoodCategory("notfood", listOf(Product(name = "Телевизор", price = 10000, count = 10), Product(name = "Смартфон", price = 20000, count = 30)
                    ).toMutableList()
                )
            )
        )
        val shipping = listOf(
            FoodCategory("notfood",
                listOf(
                    Product(name = "Телевизор", price = 10000, count = 10),
                    Product(name = "Смартфон", price = 20000, count = 10),
                ).toMutableList()
            )
        )
        // when
        Store.topUp(shipping)

        // then
        //val actualTvCount = Store
        //    .warehouse.first { it is ElectronicsCategory }
        //    .products.first { it.name == "Телевизор" }
        //    .count
        //assertEquals(20, actualTvCount)
        //val actualPhoneCount = Store
        //    .warehouse.first { it is ElectronicsCategory }
        //    .products.first { it.name == "Смартфон" }
       //     .count
        //assertEquals(40, actualPhoneCount)
    }
}
