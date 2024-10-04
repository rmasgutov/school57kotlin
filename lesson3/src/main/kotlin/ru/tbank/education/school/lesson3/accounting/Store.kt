package ru.tbank.education.school.lesson3.accounting

object Store {
    var sales = mutableListOf<Product>()
    var warehouse = mutableListOf<Category>()

    fun topUp(newCategories: List<Category>) {
        warehouse.addAll(newCategories)
    }

    fun sell(sellProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (product in category.products) {
                if (product == sellProduct) {
                    when (category) {
                        is FoodCategory -> product.count -= sellProduct.count * 2
                        is ElectronicsCategory -> product.count -= 1
                        else -> product.count -= sellProduct.count
                    }
                    sales.add(sellProduct)
                    break
                }
            }
        }
    }

    fun search(name: String): MutableList<Product> {
        return warehouse.flatMap { it.findProducts(name) }.toMutableList()
    }

    fun add(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            var existingProduct = category.products.find { it == newProduct }
            if (existingProduct != null) {
                existingProduct += newProduct
            } else {
                category.products.add(newProduct)
            }
        }
    }

    fun del(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            var existingProduct = category.products.find { it == newProduct }
            if (existingProduct != null) {
                existingProduct -= newProduct
                if (existingProduct.count <= 0) {
                    category.products.remove(existingProduct)
                }
            }
        }
    }

    fun change(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (i in category.products.indices) {
                if (category.products[i] == newProduct) {
                    category.products[i] = newProduct
                    break
                }
            }
        }
    }

    fun fullPrice(category: Category): Double {
        return category.products.sumOf { it.price * it.count }
    }
}