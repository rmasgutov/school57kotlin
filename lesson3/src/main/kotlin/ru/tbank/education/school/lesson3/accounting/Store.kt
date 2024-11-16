package ru.tbank.education.school.lesson3.accounting

object Store {
    private val sales = mutableListOf<Product>()

    private var warehouse = mutableListOf<Category>()

    fun init(warehouse: List<Category>) {
        this.warehouse = warehouse.toMutableList()
        sales.clear()
    }

    fun topUp(newCategories: List<Category>) {
        for (category in newCategories) {
            val existingCategory = warehouse.find { it.name == category.name }
            if (existingCategory != null) {
                existingCategory.products.addAll(category.products)
                existingCategory.inventoryManagement()
            } else {
                warehouse.add(category)
            }
        }
    }

    fun sell(product: Product) {
        val category = warehouse.find { it.products.any { it.name == product.name } }
        if (category != null) {
            val foundProduct = category.products.find { it.name == product.name }
            if (foundProduct != null && foundProduct.count >= product.count) {
                when (category) {
                    is FoodCategory -> {
                        val quantityToSell = product.count * 2
                        if (foundProduct.count >= quantityToSell) {
                            foundProduct.count -= quantityToSell
                            sales.add(product.copy(count = quantityToSell))
                        } else {
                            println("Недостаточно товара: ${product.name}")
                        }
                    }
                    is ElectronicsCategory -> {
                        if (foundProduct.count > 0) {
                            foundProduct.count -= 1
                            sales.add(product.copy(count = 1))
                        } else {
                            println("Товар ${product.name} закончился")
                        }
                    }
                    else -> {
                        foundProduct.count -= product.count
                        sales.add(product)
                    }
                }

                if (foundProduct.count <= 0) {
                    category.products.remove(foundProduct)
                }
            } else {
                println("Недостаточно товара для продажи: ${product.name}")
            }
        } else {
            println("Товар ${product.name} не найден на складе")
        }
    }

    fun search(searchString: String): List<Product> {
        return warehouse.flatMap { it.findProducts(searchString) }
    }

    fun calculateTotalValue(categoryName: String): Double {
        val category = warehouse.find { it.name == categoryName }
        return category?.products?.sumOf { it.price * it.count } ?: 0.0
    }

    fun showSales() {
        if (sales.isEmpty()) {
            println("Продажи отсутствуют")
        } else {
            sales.forEach { println("Продано: ${it.name}, количество: ${it.count}, цена: ${it.price}") }
        }
    }
}


