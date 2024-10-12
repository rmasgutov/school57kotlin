package ru.tbank.education.school.lesson3.accounting

abstract class Category(val name: String, var products: MutableList<Product>) {

    open fun findProducts(query: String): MutableList<Product> {
        if (name.contains(query, ignoreCase = true)) return products
        return products.filter { it.name.contains(query, ignoreCase = true) }.toMutableList()
    }

    open fun inventoryManagement() {
        val map = mutableMapOf<String, Product>()
        for (product in products) {
            map[product.name] = (map[product.name] ?: product) + product
        }
        products = map.values.toMutableList()
    }
}