package ru.tbank.education.school.lesson3.accounting

abstract class Category(val name: String, var products: MutableList<Product>) {
    fun findProducts(searchString: String): List<Product> {
        return if (name.contains(searchString, ignoreCase = true)) {
            products
        } else {

            products.filter { it.name.contains(searchString, ignoreCase = true) }
        }
    }

    fun inventoryManagement() {
        val groupedProducts = products.groupBy { it.name }
        products = groupedProducts.map { (name, productList) ->
            productList.reduce { acc, product -> acc + product }
        }.toMutableList()
    }
}


