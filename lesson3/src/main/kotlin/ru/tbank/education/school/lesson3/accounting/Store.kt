package ru.tbank.education.school.lesson3.accounting

object Store {
    val sales = mutableListOf<Product>()
    val warehouse = mutableListOf<Category>()

    fun topUp(categories : List<Category>) {
        warehouse.addAll(categories)
    }

    fun sell(productToSales : Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (product in category.listOfProducts) {
                if (product == productToSales) {
                    if (category is FoodCategory) {
                        product.count -= product.count * 2
                    } else if (category is ElectronicsCategory) {
                        product.count--
                    } else {
                        product.count -= productToSales.count
                    }
                }
            }
        }
    }

    fun search(productToSearch : String) : List<Product> {
        var listOfProducts = mutableListOf<Product>()
        for (category in warehouse) {
            category.inventoryManagement()
            listOfProducts.addAll(category.listOfProducts)
        }
        return listOfProducts.filter { it.name.contains(productToSearch) }
    }

    fun add(product : Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (num in 0..category.listOfProducts.size) {
                if (category.listOfProducts[num] == product) {
                    category.listOfProducts[num] += product
                }
            }
        }
    }

    fun del(product : Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (num in 0..category.listOfProducts.size) {
                if (category.listOfProducts[num] == product) {
                    category.listOfProducts[num] -= product
                }
            }
        }
    }

    fun countFullCost(category: Category) : Int{
        var fullCost = 0
        for (product in category.listOfProducts) {
            fullCost += product.count
        }
        return fullCost
    }

    fun change(changeProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (i in 0..category.listOfProducts.size) {
                if (category.listOfProducts[i].name == changeProduct.name) {
                    category.listOfProducts[i] = changeProduct
                }
            }
        }
    }
}
