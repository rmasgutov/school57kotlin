package ru.tbank.education.school.lesson3.accounting


object Store {
    var sales = mutableListOf<Product>()
    var warehouse = mutableListOf<Category>()

    fun topUp(newCategories: List<Category>) {
        for (category in newCategories) {
            warehouse.add(category)
        }
    }

    fun sell(sellProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (product in category.products) {
                if (product != sellProduct) {
                    continue
                }
                if (category is FoodCategory) {
                    product.count -= sellProduct.count * 2
                } else if (category is ElectronicsCategory) {
                    product.count--
                } else {
                    product.count -= sellProduct.count
                }
            }
        }
        sales.add(sellProduct)
    }

    fun search(name: String): MutableList<Product> {
        val foundProducts = mutableListOf<Product>()
        for (category in warehouse) {
            foundProducts.addAll(category.findProducts(name))
        }
        return foundProducts
    }

    fun add(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (i in 0..category.products.size) {
                if (category.products[i] == newProduct) {
                    category.products[i] += newProduct
                }
            }
        }
    }

    fun del(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (i in 0..category.products.size) {
                if (category.products[i] == newProduct) {
                    category.products[i] -= newProduct
                }
            }
        }
    }

    fun change(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (i in 0..category.products.size) {
                if (category.products[i] == newProduct) {
                    category.products[i] = newProduct
                }
            }
        }
    }

    fun fullPrice(category: Category): Int {
        var result = 0
        for (product in category.products) {
            result += product.price * product.count
        }
        return result
    }
}
