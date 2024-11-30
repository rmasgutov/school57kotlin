package ru.tbank.education.school.lesson3.accounting


class ElectronicsCategory(name: String, products: List<Product>) : Category(name, products) {
    fun findProducts(request: String): Any {
        if (request in "Electronics") {
            return products
        } else {
            return products.filter { request in this.name }
        }
    }
}