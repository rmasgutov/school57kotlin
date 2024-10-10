package ru.tbank.education.school.lesson3.accounting


class ElectronicsCategory(name: String, products: MutableList<Product>) : Category(name, products) {
    fun findProducts(search: String): Any {
        if (search in "Electronics") {
            return products
        } else {
            return products.filter { search in this.name }
        }
    }
}