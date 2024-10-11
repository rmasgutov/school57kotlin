package ru.tbank.education.school.lesson3.accounting

object Store {
    val warehouse = mutableListOf<Category>()
    val sales = mutableListOf<Product>()

    fun topUp(categories: List<Category>) {
        warehouse.addAll(categories)
    }

    fun search(query: String): List<Product> {
        return warehouse.flatMap { it.findProducts(query) }.toMutableList()
    }

    fun sell(sellingProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (product in category.products) {
                if (product == sellingProduct) {
                    if (category is FoodCategory) {
                        product.count -= product.count * 2
                    } else if (category is ElectronicsCategory) {
                        product.count--
                    } else {
                        product.count -= sellingProduct.count
                    }
                    sales.add(sellingProduct)
                }
            }
        }
    }

    fun add(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            var existsProduct = category.products.find { it == newProduct }
            if (existsProduct != null) {
                existsProduct += newProduct
            } else {
                category.products.add(newProduct)
            }
        }
    }

    fun remove(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            var existsProduct = category.products.find { it == newProduct }
            if (existsProduct != null) {
                existsProduct -= newProduct
                if (existsProduct.count <= 0) {
                    category.products.remove(existsProduct)
                }
            }
        }
    }

    fun change(changeProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (ind in category.products.indices) {
                if (category.products[ind] == changeProduct) {
                    category.products[ind] = changeProduct
                    break
                }
            }
        }
    }

    fun fullPrice(category: Category): Double {
        return category.products.sumOf { it.price * it.count }
    }
}


