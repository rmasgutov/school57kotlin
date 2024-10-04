package ru.tbank.education.school.lesson3.accounting

abstract class Category(val name: String, var products: MutableList<Product>) {
    fun findProducts(request: String): MutableList<Product> {
        return products.filter { it.name.contains(request) || name.contains(request) }.toMutableList()
    }

    fun inventoryManagement() {
        this.products =
            this.products
                .groupBy { it.name }
                .map { (_, products) -> products.reduce { p, pn -> p + pn } }
                .toMutableList()
    }
}
