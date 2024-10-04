package ru.tbank.education.school.lesson3.accounting

data class Product(val name: String, var price: Double = 0.0, var count: Int) {
    override fun equals(other: Any?): Boolean {
        return other is Product && other.name == this.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    operator fun plus(other: Product): Product {
        assert(this == other) { "You can't sum products with different names!" }
        return Product(this.name, this.price, this.count + other.count)
    }
}
