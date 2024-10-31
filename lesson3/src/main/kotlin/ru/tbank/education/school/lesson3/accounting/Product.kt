package ru.tbank.education.school.lesson3.accounting

data class Product(
    var name: String,
    var price: Double,
    var count: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Product) return false
        return name == other.name
    }

    operator fun plus(other: Product): Product {
        if (name != other.name) {
            throw IllegalArgumentException("Cannot add products with different names")
        }
        return Product(name, price, count + other.count)
    }

    operator fun minus(other: Product): Product {
        if (name != other.name) {
            throw IllegalArgumentException("Cannot subtract products with different names")
        }
        return Product(name, price, count - other.count)
    }
}