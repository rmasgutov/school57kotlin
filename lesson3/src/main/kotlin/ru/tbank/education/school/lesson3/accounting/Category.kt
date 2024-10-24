package ru.tbank.education.school.lesson3.accounting

abstract class Category (val name : String, var listOfProducts : MutableList<Product>) {
    open fun findProducts (request : String) : List<Product>{
        if (name.contains((request))) {
            return listOfProducts
        }
        return listOfProducts.filter { it.name.contains(request) }
    }

    open fun inventoryManagement() {
        var result = mutableListOf<Product>()
        var used = mutableSetOf<String>()
        for (product in listOfProducts) {
            var resProduct = product
            if (used.contains(product.name)) continue
            for (anotherProduct in listOfProducts) {
                if (anotherProduct === product) continue
                if (anotherProduct.name == product.name) {
                   resProduct += anotherProduct
                }
            }
            result.add(resProduct)
        }
        listOfProducts = result
    }
}