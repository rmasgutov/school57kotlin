package ru.tbank.education.school.lesson3.accounting

abstract class Category(
    val name: String,
    var products: MutableList<Product>
) {
    fun findProducts(productName: String): List<Product> {
        if (productName == name) return products
        return products.filter { it.name.contains(productName) }
    }
    fun inventoryManagement() {
        products.groupBy { it.name }
        var item = 0
        while (item < products.size) {
            if (products[item].name == products[item + 1].name) {
                products[item].count += products[item + 1].count
                products.removeAt(item + 1)
            }
            item++
        }
    }
}