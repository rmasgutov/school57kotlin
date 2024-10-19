package ru.tbank.education.school.lesson3.accounting

data class Product(
        val name: String,
        val price: Int,
        var count: Int
) {

    override fun equals(other: Any?): Boolean {
        return if (other is Product) {
            name == other.name
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    operator fun plus(other: Product): Product {
        return if (this == other) {
            Product(name, price, count + other.count)
        } else {
            throw IllegalArgumentException()
        }
    }

}