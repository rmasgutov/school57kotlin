package ru.tbank.education.school.lesson3.accounting

data class Product(
    var name: String,
    var price: Int,
    var count: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Product)
                return false
        return name == other.name
    }


    operator fun plus(other: Product): Product {
        if (this == other) {
            return Product(name, price, count - other.count)
        }
        return this
    }


    operator fun minus(other: Product) : Product {
        if (this == other) {
            return Product(name, price, count - other.count)
        }
        return this
    }


    operator fun times(other: Int): Product {
        return Product(this.name, this.price, this.count * other)
    }
}


abstract class Category(
    var name: String,
    var products: MutableList<Product>
) {
    fun inventoryManagement() {
        var ans: MutableList<Product> = mutableListOf()
        var def: MutableSet<String> = mutableSetOf("")
        for (i in 0..products.size - 1) {
            var loc: Product = products[i]
            if (def.containsAll(listOf(loc.name))) continue
            def.add(loc.name)
            for (j in 0..products.size - 1) {
                if (i == j) continue
                val loc2 = products[i]
                loc = loc + loc2
            }
            ans.add(loc)
        }
        products = ans
    }
}


class FoodCategory(name: String, products: MutableList<Product>) : Category(name, products) {
    fun findProducts(search: String): Any {
        if (search in "Food") {
            return products
        } else {
            return products.filter { search in this.name }
        }
    }
}


class ElectronicsCategory(name: String, products: MutableList<Product>) : Category(name, products) {
    fun findProducts(search: String): Any {
        if (search in "Electronics") {
            return products
        } else {
            return products.filter { search in this.name }
        }
    }
}


object Store {
    var sales: MutableList<Product> = mutableListOf()
    var food = FoodCategory("food", mutableListOf())
    var elec = ElectronicsCategory("electronics", mutableListOf())
    val warehouse: MutableList<Category> = mutableListOf(food, elec)


    fun topUp(cat: Category) {
        warehouse.add(cat)
    }


    fun sell(obj: Product) {
        food.inventoryManagement()
        elec.inventoryManagement()
        for (i in 0..food.products.size - 1) {
            food.products[i] = food.products[i] - obj * 2
        }
        for (i in 0..elec.products.size - 1) {
            if (obj.name == elec.products[i].name) {
                elec.products[i].count -= 1
            }
        }
        for (i in 0..warehouse.size - 1) {
            warehouse[i].inventoryManagement()
            if (warehouse[i].name == "food" || warehouse[i].name == "electronics") continue
            for (j in 0..warehouse[i].products.size) {
                warehouse[i].products[j] = warehouse[i].products[j] - obj
            }
        }
        sales.add(obj)
    }


    fun search(obj: String): Product {
        for (i in 0..warehouse.size - 1) {
            warehouse[i].inventoryManagement()
            for (j in 0..warehouse[i].products.size - 1) {
                if (warehouse[i].products[j].name == obj) {
                    return warehouse[i].products[j]
                }
            }
        }
        return TODO("There`s not this product")
    }


    fun add(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            var existingProduct = category.products.find { it == newProduct }
            if (existingProduct != null) {
                existingProduct += newProduct
            } else {
                category.products.add(newProduct)
            }
        }
    }


    fun del(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            var existingProduct = category.products.find { it == newProduct }
            if (existingProduct != null) {
                existingProduct -= newProduct
                if (existingProduct.count <= 0) {
                    category.products.remove(existingProduct)
                }
            }
        }
    }


    fun change(newProduct: Product) {
        for (category in warehouse) {
            category.inventoryManagement()
            for (i in category.products.indices - 1) {
                if (category.products[i] == newProduct) {
                    category.products[i] = newProduct
                    break
                }
            }
        }
    }
    fun full_price(category: Category): Int {
        var ans = 0
        for (i in 0..category.products.size - 1){
            ans += category.products[i].price * category.products[i].count
        }
        return ans
    }
}