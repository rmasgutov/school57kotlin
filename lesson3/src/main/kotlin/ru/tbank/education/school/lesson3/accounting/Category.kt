package ru.tbank.education.school.lesson3.accounting

abstract class Category (val name: String, var products: MutableList<Product>) {
    abstract fun findProducts(good: String) : MutableList<Product>

    abstract fun inventoryManagement()
}