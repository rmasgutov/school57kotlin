package ru.tbank.education.school.lesson3.accounting


data class Product(val name: String, var price: Double, var count: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false
        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    operator fun plus(other: Product): Product {
        if (this == other) {
            return copy(count = this.count + other.count)
        }
        return this
    }
}

abstract class Category(val name: String, var products: MutableList<Product>) {
    abstract fun findProducts(query: String): List<Product>
    abstract fun inventoryManagement()

    fun addProduct(product: Product) {
        val existingProduct = products.find { it == product }
        if (existingProduct != null) {
            existingProduct.count += product.count
        } else {
            products.add(product)
        }
    }

    fun removeProduct(product: Product) {
        products.removeIf { it == product }
    }

    fun updateProduct(product: Product, newCount: Int) {
        val existingProduct = products.find { it == product }
        existingProduct?.let {
            it.count = newCount
        }
    }

    fun calculateTotalValue(): Double {
        return products.sumOf { it.price * it.count }
    }
}

class FoodCategory(name: String, products: MutableList<Product> = mutableListOf()) : Category(name, products) {
    override fun findProducts(query: String): List<Product> {
        if (name.contains(query, ignoreCase = true)) {
            return products.toList()
        }
        return products.filter { it.name.contains(query, ignoreCase = true) }
    }

    override fun inventoryManagement() {
        val groupedProducts = products.groupBy { it.name }.mapValues { (_, products) ->
            products.reduce { acc, product -> acc + product }
        }
        products.clear()
        products.addAll(groupedProducts.values)
    }
}

class ElectronicsCategory(name: String, products: MutableList<Product> = mutableListOf()) : Category(name, products) {
    override fun findProducts(query: String): List<Product> {
        if (name.contains(query, ignoreCase = true)) {
            return products.toList()
        }
        return products.filter { it.name.contains(query, ignoreCase = true) }
    }

    override fun inventoryManagement() {
        val groupedProducts = products.groupBy { it.name }.mapValues { (_, products) ->
            products.reduce { acc, product -> acc + product }
        }
        products.clear()
        products.addAll(groupedProducts.values)
    }
}

object Store {
    private var warehouse: MutableList<Category> = mutableListOf()
    private var sales: MutableList<Product> = mutableListOf()

    fun init(warehouse: List<Category>) {
        this.warehouse = warehouse.toMutableList()
        sales.clear()
    }

    fun topUp(categories: List<Category>) {
        categories.forEach { category ->
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
        val category = warehouse.find { it.products.any { p -> p == product } }
        category?.let {
            val existingProduct = it.products.find { p -> p == product }
            if (existingProduct != null) {
                when (it) {
                    is FoodCategory -> {
                        val amountToSell = product.count * 2
                        if (existingProduct.count >= amountToSell) {
                            existingProduct.count -= amountToSell
                            sales.add(product.copy(count = amountToSell))
                        } else {
                            println("Not enough stock for food item: ${product.name}")
                        }
                    }
                    is ElectronicsCategory -> {
                        if (existingProduct.count > 0) {
                            existingProduct.count -= 1
                            sales.add(product.copy(count = 1))
                        } else {
                            println("Not enough stock for electronic item: ${product.name}")
                        }
                    }

                    else -> {
                        println("Product isn't Food or Electronics")
                    }
                }
            } else {
                println("Product not found: ${product.name}")
            }
        } ?: println("Category not found: ${product.name}")
    }

    fun search(query: String): List<Product> {
        return warehouse.flatMap { it.findProducts(query) }
    }

    fun addProduct(categoryName: String, product: Product) {
        val category = warehouse.find { it.name == categoryName }
        category?.addProduct(product) ?: println("Category not found: $categoryName")
    }

    fun removeProduct(categoryName: String, product: Product) {
        val category = warehouse.find { it.name == categoryName }
        category?.removeProduct(product) ?: println("Category not found: $categoryName")
    }

    fun updateProduct(categoryName: String, product: Product, newCount: Int) {
        val category = warehouse.find { it.name == categoryName }
        category?.updateProduct(product, newCount) ?: println("Category not found: $categoryName")
    }

    fun calculateTotalValue(categoryName: String): Double {
        val category = warehouse.find { it.name == categoryName }
        return category?.calculateTotalValue() ?: 0.0
    }
}

fun main() {
    val apple = Product("Apple", 1.0, 100)
    val banana = Product("Banana", 0.5, 150)
    val orange = Product("Orange", 1.5, 200)
    val tv = Product("TV", 500.0, 10)
    val phone = Product("Phone", 300.0, 20)

    val fruits = FoodCategory("Fruits", mutableListOf(apple, banana, orange))
    val electronics = ElectronicsCategory("Electronics", mutableListOf(tv, phone))

    Store.init(listOf(fruits, electronics))

    Store.sell(Product("Apple", 1.0, 1))
    Store.sell(Product("TV", 500.0, 1))

    println("Search for 'Apple':")
    println(Store.search("Apple"))

    Store.addProduct("Fruits", Product("Grape", 2.0, 50))
    Store.addProduct("Electronics", Product("Laptop", 1000.0, 5))

    Store.removeProduct("Fruits", Product("Banana", 0.5, 150))

    Store.updateProduct("Fruits", Product("Apple", 1.0, 100), 150)

    println("Total value of Fruits: ${Store.calculateTotalValue("Fruits")}")
    println("Total value of Electronics: ${Store.calculateTotalValue("Electronics")}")
}