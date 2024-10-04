package ru.tbank.education.school.lesson3.accounting

import kotlin.assert

object Store {
    val sales = mutableListOf<Product>()
    val warehouse = mutableListOf<Category>()

    fun init(warehouse: List<Category>) {
        this.warehouse.addAll(warehouse)
    }

    fun topUp(categories: List<Category>) {
        warehouse.addAll(categories)
    }

    fun sell(product: Product) {
        warehouse.forEach { category ->
            val foundProduct = category.findProducts(product.name).firstOrNull() ?: return@forEach

            category.inventoryManagement()
            val sellAmount =
                when (category) {
                    is FoodCategory -> 2
                    is ElectronicsCategory -> 1
                    else -> 1
                }
            foundProduct.apply {
                assert(count >= sellAmount) { "Not enough \"${product.name}\" to sell!" }
                count -= sellAmount
            }

            sales.add(product)
            category.products.removeAll { product -> product.count == 0 }
        }
    }

    fun search(query: String): List<Product> {
        return warehouse.map { it.findProducts(query) }.flatten()
    }

    fun addProduct(product: Product) {
        warehouse
            .first { it.findProducts(product.name).isNotEmpty() }
            .apply { inventoryManagement() }
            .findProducts(product.name)
            .firstOrNull()!!
            .apply { count += product.count }
    }

    fun removeProduct(product: Product) {
        warehouse.first { it.findProducts(product.name).isNotEmpty() }.products.removeAll {
            it == product
        }
    }

    fun countCategoryPrice(category: Category): Double {
        return category.products.sumOf { it.price }
    }
}
