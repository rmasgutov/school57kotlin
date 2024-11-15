package ru.tbank.education.school.lesson3.accounting


data class Product(
    var name: String,
    var price: Double,
    var count: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other is Product) {
            return name == other.name
        }
        return false
    }


    operator fun plus(other: Product): Product {
        if (this == other) {
            return Product(this.name, this.price, this.count + other.count)
        }
        return this
    }


    operator fun minus(other: Product): Product {
        if (this == other) {
            return Product(this.name, this.price, this.count - other.count)
        }
        return this
    }


    operator fun times(other: Int): Product {
        return Product(this.name, this.price, this.count * other)
    }
}
