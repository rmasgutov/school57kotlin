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