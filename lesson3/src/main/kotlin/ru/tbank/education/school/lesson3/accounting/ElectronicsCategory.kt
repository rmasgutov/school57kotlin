package ru.tbank.education.school.lesson3.accounting

class ElectronicsCategory(name: String, products: MutableList<Product>) : Category(name, products) {
    constructor(products: List<Product>) : this("", products.toMutableList())
}
