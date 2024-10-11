package ru.tbank.education.school.lesson3
//  products


data class Product(
    val name:String,
    var price:Int,
    var count:Int,
    ){

    fun equals(x: Product) : Boolean {
        return (x.name == name)
    }

    operator fun plus(a:Product):Product {
        return Product(name, price,a.count + count)
    }

    operator fun minus(a:Product) : Product {
        return Product(name, price, count - a.count)
    }
}


abstract class Category(
    val name:String,
    var products:MutableList<Product>
) {
    fun findProducts(st: String) : List<String> {
        var arr_of_names = listOf("")
        if (st in name) {
            for (prod in products) {
                arr_of_names.plus(prod.name)
            }
        }
        return arr_of_names
    }

    fun inventoryManagement() {
        val map = mutableMapOf<String, Product>()
        for (product in products) {
            map[product.name] = (map[product.name] ?: product) + product
        }
        products = map.values.toMutableList()
    }
    fun sell(c: Int, ind: Int):Boolean {
        if(this.products[ind].count>1){
            this.products[ind].count -= 1
        }
        else{
            this.products[ind].count = 0
            return true
        }
        return false
    }
}


class ElectronicsCategory(name: String, products: MutableList<Product>) : Category(name, products)



class FoodCategory(name: String, products: MutableList<Product>) : Category(name, products)



class Store(
    var sales:MutableList<Product>,
    var warehouse:MutableList<Category>){
    fun topUp(c: Category){
        warehouse.add(c)
    }
    fun sell(p:Product, c:Int){
        val ind:Pair<Int, Int> = search(p.name)
        if (ind != Pair(-1, -1)){
            if (warehouse[ind.first].sell(c, ind.second)){
                sales.add(p)
            }
        }
    }
    fun delete(p:Product){
        val a:Pair<Int, Int> = this.search(p.name)
        this.warehouse[a.first].products.removeAt(a.second)
    }
    fun search(s:String):Pair<Int, Int>{
        for(i in (0 .. this.warehouse.size-1)){
            for(j in (0 .. this.warehouse[i].products.size-1)){
                if (this.warehouse[i].products[j].name == s){
                    return Pair(i, j)
                }
            }
        }
        return Pair(-1,-1)
    }
}