package ru.tbank.education.school.lesson3.accounting

data class Product (
    val name: String,
    val price: Int,
    val count: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Product) return false
        return name == other.name
    }

    operator fun plus(other: Product) : Product {
        return Product(name, price, count + other.count)
    }
}
