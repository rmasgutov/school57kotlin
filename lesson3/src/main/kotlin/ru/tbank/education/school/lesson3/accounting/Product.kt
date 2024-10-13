package ru.tbank.education.school.lesson3.accounting

data class Product(
    val name: String,
    val price: Int,
    var count: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other is Product) return other.name == name
        return false
    }

    operator fun plus(other: Product): Product? {
        if (name == other.name) {
            return Product(name, price, count + other.count)
        }
        println("Can't sum product not with product.")
        return null
    }
}