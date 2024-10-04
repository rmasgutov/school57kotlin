package ru.tbank.education.school.lesson3.accounting

abstract class Category (val name: String, var products: MutableList<Product>) {
    open fun findProducts(good: String) : MutableList<Product> {
        if (name.contains((good))) {
            return products
        }
        val foundProducts = mutableListOf<Product>()
        for (product in products) {
            if (product.name.contains(good)) {
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