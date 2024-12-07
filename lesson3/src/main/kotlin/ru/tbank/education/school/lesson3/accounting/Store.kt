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
    fun removeProduct(categoryName: String, product: Product) {
        val category = warehouse.find { it.name == categoryName }
        if (category != null) {
            category.del(product)
        }
        else println("This category is empty!")
    }

    fun updateProduct(categoryName: String, product: Product, newCount: Int) {
        val category = warehouse.find { it.name == categoryName }
        if (category != null) {
            category.change(product, newCount)
        }
        else println("This category is empty!")
    }
}