package ru.tbank.education.school.lesson3.accounting

data class Product(
    val name : String,
    var price : Double,
    var count : Int
) {
    override fun equals(thing: Any?): Boolean {
        if (thing !is Product) return false
        return name == thing.name
    }

    operator fun plus(thing: Product) : Product {
        return Product(name, price, count + thing.count)
    }

    operator fun minus(thing: Product) : Product{
        return Product(name, price, count - thing.count)
    }
}

