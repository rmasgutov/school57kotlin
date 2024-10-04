package ru.tbank.education.school.lesson3.accounting

object Store {
    val sales = mutableListOf<Product>()
    val warehouse = mutableListOf<Category>()

    fun topUp(categories: List<Category>) {
        warehouse.addAll(categories)
    }
    fun sell(product: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (item in category.products) {
                if (item == product) {
                    if (category is FoodCategory) item.count -= product.count * 2
                    else if (category is ElectronicsCategory) item.count--
                    else item.count -= product.count
                }
            }
        }
        sales.add(product)
    }
    fun search(name: String): MutableList<Product> {
        val foundProducts: MutableList<Product> = mutableListOf()
        for (category in warehouse) {
            foundProducts.addAll(category.findProducts(name))
        }
        return foundProducts
    }
    fun add(product: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            var thisProduct = category.products.find { it == product }
            if (thisProduct != null) thisProduct += product
            else category.products.add(product)
        }
    }
    fun change(product: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (item in 0..category.products.size) {
                if (category.products[item] == product) {
                    category.products[item] = product
                    return
                }
            }
        }
    }
    fun del(product: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (item in 0..category.products.size) {
                if (category.products[item] == product) {
                    category.products.remove(product)
                    return
                }
            }
        }
    }
}