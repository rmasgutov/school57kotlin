package ru.tbank.education.school.lesson3.accounting
data class Product(
    val name: String,
    var price: Double,
    var count: Int
) {

    override fun equals(other: Any?): Boolean {
        if (other !is Product) return false
        return name == other.name
    }

    operator fun plus(other: Product): Product {
        return Product(name, price, this.count + other.count)
    }

    operator fun minus(other: Product) : Product {
        return Product(name, price, this.count - other.count)
    }
}