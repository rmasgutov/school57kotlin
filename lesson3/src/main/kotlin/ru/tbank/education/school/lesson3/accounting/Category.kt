package ru.tbank.education.school.lesson3.accounting

abstract class Category(val name: String, var products: MutableList<Product>) {
    fun findProducts(name: String): MutableList<Product> {
        if (name in this.name) return products
    }
}
