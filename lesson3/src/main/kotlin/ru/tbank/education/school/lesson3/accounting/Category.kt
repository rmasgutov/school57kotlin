package ru.tbank.education.school.lesson3.accounting

abstract class Category(val name: String, var products: MutableList<Product>) {
    fun findProducts(request: String): MutableList<Product> {
        val result = products
        if (!name.contains(request)) {
            result.filter { it.name.contains(request) || name.contains(request) }.toMutableList()
        }
        return result
    }

    fun inventoryManagement() {
        this.products =
            this.products
                .groupBy { it.name }
                .map { (_, products) -> products.reduce { p, pn -> p + pn } }
                .toMutableList()
    }
}

fun main() {
    val category = ElectronicsCategory(products = listOf(
        Product(name = "Телевизор", price = 10000.0, count = 10),
        Product(name = "Смартфон", price = 10000.0, count = 10)
    ))
    println(category.findProducts("Теле"))
}