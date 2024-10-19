package ru.tbank.education.school.lesson3.accounting

abstract class Category(
    val name: String,
    var products: MutableList<Product>
) {
    fun del(product: Product) {
        products.removeIf { it == product }
    }
    fun change(product: Product, newCount: Int) {
        val existingProduct = products.find { it == product }
        existingProduct?.let {
            it.count = newCount
        }
    }
    fun findProducts(productName: String): List<Product> {
        if (productName == name) return products
        return products.filter { it.name.contains(productName) }
    }
    fun inventoryManagement() {
        products.groupBy { it.name }.mapValues { (_, products) ->
            products.reduce { acc, product -> acc + product }
        }
    }
}