package ru.tbank.education.school.lesson3.accounting

import ru.tbank.education.school.lesson3.accounting.impl.FoodCategory

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
object Store {

    val sales = mutableListOf<Product>()
    val warehouse = mutableListOf<Category>()

    fun init(warehouse: List<Category>) {
        this.sales.clear()
        this.warehouse.clear()
        this.warehouse.addAll(warehouse)
    }

    fun topUp(categories: List<Category>) {
        categories.forEach { category ->
            if (warehouse.any { it.name == category.name }) {
                warehouse.first { it.name == category.name }
                    .products.addAll(category.products)
            } else {
                warehouse.add(category)
            }
        }

        warehouse.forEach { it.inventoryManagement() }
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

    fun countTotalPrice(): Double {
        return warehouse.map { category ->
            category.products.map { it.count * it.price }
        }.flatten().sum()
    }

    fun sell(product: Product) {
        warehouse.forEach { category ->
            category.inventoryManagement()

            val foundProduct = category.products.find { it == product }
            if (foundProduct == null) {
                throw IllegalArgumentException("Product not found")
            }

            val toRemove = if (category is FoodCategory) 2 * product.count else product.count

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