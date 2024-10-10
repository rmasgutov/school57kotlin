package ru.tbank.education.school.lesson3.accounting


class FoodCategory(name: String, products: MutableList<Product>) : Category(name, products) {
    fun findProducts(search: String): Any {
        if (search in "Food") {
            return products
        } else {
            return products.filter { search in this.name }
        }
    }
}