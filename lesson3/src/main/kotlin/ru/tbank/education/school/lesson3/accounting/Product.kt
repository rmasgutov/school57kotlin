package ru.tbank.education.school.lesson3.accounting

data class Product(val name: String, var price: Double, var count: Int) {
    override fun equals(other: Any?): Boolean {
        if (other is Product) return other.name == this.name
        return false
    }

    operator fun plus(other: Product): Product {
        assert(this == other)
        return Product(this.name, this.price, this.count + other.count)
    }
}
