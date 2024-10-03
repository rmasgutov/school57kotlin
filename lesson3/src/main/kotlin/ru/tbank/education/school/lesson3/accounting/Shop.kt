package ru.tbank.education.school.lesson3.accounting


data class Product(
    var name: String,
    var price: Int,
    var count: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other is Product) {
            return name == other.name
        }
        return false
    }


    operator fun plus(other: Product): Product {
        if (this.name == other.name) {
            return Product(this.name, this.price, this.count + other.count)
        }
        return this
    }


    operator fun minus(other: Product): Product {
        if (this.name == other.name) {
            return Product(this.name, this.price, this.count - other.count)
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
        var used: MutableSet<String> = mutableSetOf("")
        for (i in 0..products.size) {
            var loc: Product = products[i]
            if (used.containsAll(listOf(loc.name))) continue
            used.add(loc.name)
            for (j in 0..products.size) {
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
        for (i in 0..food.products.size) {
            food.products[i] = food.products[i] - obj * 2
        }
        for (i in 0..elec.products.size) {
            if (obj.name == elec.products[i].name) {
                elec.products[i].count -= 1
            }
        }
        for (i in 0..warehouse.size) {
            warehouse[i].inventoryManagement()
            if (warehouse[i].name == "food" || warehouse[i].name == "electronics") continue
            for (j in 0..warehouse[i].products.size) {
                warehouse[i].products[j] = warehouse[i].products[j] - obj
            }
        }
        sales.add(obj)
    }


    fun search(obj: String): Product {
        for (i in 0..warehouse.size) {
            warehouse[i].inventoryManagement()
            for (j in 0..warehouse[i].products.size) {
                if (warehouse[i].products[j].name == obj) {
                    return warehouse[i].products[j]
                }
            }
        }
        return TODO("There`s not this product")
    }


    fun add(obj: Product) {
        for (i in 0..warehouse.size) {
            warehouse[i].inventoryManagement()
            for (j in 0..warehouse[i].products.size) {
                warehouse[i].products[j] = warehouse[i].products[j] + obj
            }
        }
    }


    fun del(obj: Product) {
        for (i in 0..warehouse.size) {
            warehouse[i].inventoryManagement()
            for (j in 0..warehouse[i].products.size) {
                warehouse[i].products[j] = warehouse[i].products[j] - obj
            }
        }
    }


    fun change(obj: Product) {
        for (i in 0..warehouse.size) {
            warehouse[i].inventoryManagement()
            for (j in 0..warehouse[i].products.size) {
                if (warehouse[i].products[j].name == obj.name) {
                    warehouse[i].products[j] = obj
                }
            }
        }
    }


    fun full_price(cat: Category): Int {
        var ans = 0
        for (i in 0..cat.products.size) {
            ans += cat.products[i].price * cat.products[i].count
        }
        return ans
    }
}