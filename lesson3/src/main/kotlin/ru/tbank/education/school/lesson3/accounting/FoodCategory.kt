package ru.tbank.education.school.lesson3.accounting


class FoodCategory(name: String, products: MutableList<Product>) : Category(name, products) {
    fun findProducts(request: String): Any {
        if (request in "Food") {
            return products
        } else {
            return products.filter { request in this.name }
        }
    }
}