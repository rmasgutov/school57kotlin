package ru.tbank.education.school.lesson3.accounting

data class Product(
    val name : String,
    val price : Double,
    var count : Int
) {
    override fun equals(thing: Any?): Boolean {
        if (thing !is Product) return false
        return name == thing.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    operator fun plus(thing: Product) : Product {
        if (name != thing.name) {
            throw ArithmeticException("Можно складывать только продукты с одинаковым именем")
        }
        return Product(name, price, count + thing.count)
    }

    operator fun minus(thing: Product) : Product{
        if (name != thing.name) {
            throw ArithmeticException("Можно складывать только продукты с одинаковым именем")
        }
        return Product(name, price, count - thing.count)
    }
}

