package ru.tbank.education.school.lesson3.accounting



data class Product(val name: String, val price: Double, var count: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false
        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    operator fun plus(other: Product): Product {
        require(this.name == other.name) { "Товары должны иметь одинаковое название для сложения" }
        return Product(this.name, this.price, this.count + other.count)
    }
}