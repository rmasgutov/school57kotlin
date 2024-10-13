package ru.tbank.education.school.lesson3.accounting


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
        food.products.map {
            it - obj * 2
        }
        elec.products.map {
            if (it == obj) it.count -= 1
        }
        warehouse.forEach {
            it.inventoryManagement()
            if (it.name != "food" && it.name != "electronics") {
                it.products.map {
                    it - obj
                }
            }
        }
        sales.add(obj)
    }


    fun search(obj: String): Product {
        warehouse.forEach {
            it.inventoryManagement()
            it.products.forEach {
                if (it.name == obj) return it
            }
        }
        return TODO("There`s not this product")
    }


    fun add(obj: Product) {
        warehouse.forEach {
            it.inventoryManagement()
            it.products.map {
                it + obj
            }
        }
    }


    fun del(obj: Product) {
        warehouse.forEach {
            it.inventoryManagement()
            it.products.map {
                it - obj
            }
        }
    }


    fun change(obj: Product) {
        warehouse.forEach {
            it.inventoryManagement()
            it.products.map {
                if (it == obj) it * 0 + obj
            }
        }
    }


    fun full_price(cat: Category): Int {
        var ans = 0
        cat.products.forEach {
            ans += it.price * it.count
        }
        return ans
    }
}