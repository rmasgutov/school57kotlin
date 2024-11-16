package ru.tbank.education.school.lesson3.accounting

import ru.tbank.education.school.lesson3.accounting.FoodCategory

object Store {

    val sales = mutableListOf<Product>()
    val warehouse = mutableListOf<Category>()

    fun topUp(categories: List<Category>) {
        warehouse.addAll(categories)
    }

    fun addProduct(product: Product) {
        warehouse.first { it.products.contains(product) }
            .apply { inventoryManagement() }
            .products
            .find { p -> p == product }!!
            .apply { count += product.count }
    }

    fun removeProduct(product: Product) {
        warehouse.first { it.products.contains(product) }
            .products
            .removeIf { it == product }
    }

    fun countTotalPrice(): Int {
        return warehouse.map { category ->
            category.products.map { it.count * it.price }
        }.flatten().sum()
    }

    fun sell(product: Product) {
        warehouse.forEach { category ->
            val foundProduct = category.products.find { it == product }
            if (foundProduct == null) {
                return@forEach
            }

            val toRemove = if (category is FoodCategory) 2 else 1
            category.inventoryManagement()

            foundProduct.apply {
                if (count < toRemove) throw IllegalArgumentException("Not enough products to sell")
                count -= toRemove
            }

            sales.add(product)
            category.products.removeAll { product ->
                product.count == 0
            }
        }
    }

    fun search(query: String): List<Product> {
        return warehouse.map { it.findProducts(query) }.flatten()
    }
}