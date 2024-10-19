package ru.tbank.education.school.lesson3.accounting


abstract class Category(
    var name: String,
    var products: MutableList<Product>
) {
    fun inventoryManagement() {
        var ans: MutableList<Product> = mutableListOf()
        var used: MutableSet<String> = mutableSetOf("")
        products.forEach {
            var loc: Product = it
            if (!used.containsAll(listOf(it.name))) {
                used.add(it.name)
                products.forEach { second ->
                    if (it != second) loc = loc + second
                }
                ans.add(loc)
            }
        }
        products = ans
    }
}