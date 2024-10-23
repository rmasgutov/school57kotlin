package ru.tbank.education.school.lesson3.accounting

abstract class Category (val name: String, var products: MutableList<Product>) {
    constructor(products: List<Product>) : this("", products.toMutableList())

    open fun findProducts(request: String) : MutableList<Product> {
        if (name.contains((request))) {
            return products
        }
        val foundProducts = mutableListOf<Product>()
        for (product in products) {
            if (product.name.contains(request)) {
                foundProducts.add(product)
            }
        }
        return foundProducts
    }

    open fun inventoryManagement() {
        val newProducts = mutableListOf<Product>()
        val was = mutableSetOf<String>()

        for (product in products) {
            if (was.contains(product.name)) {
                continue
            }
            var newProduct = product
            was.add(product.name)
            for (other in products) {
                if (other === product) {
                    continue
                }
                if (newProduct == other) {
                    newProduct += other
                }
            }
            newProducts.add(newProduct)
        }
        products = newProducts
    }
}