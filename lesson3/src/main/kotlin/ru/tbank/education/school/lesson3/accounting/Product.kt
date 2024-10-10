package ru.tbank.education.school.lesson3.accounting

data class Product (
    val name: String = "",
    val price: Double = 0.0,
    var count: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Product) return false
        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    operator fun plus(other: Product) : Product {
        return Product(name, price, count + other.count)
    }

    operator fun minus(other: Product) : Product {
        return Product(name, price, count - other.count)
    }
}
