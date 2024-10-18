package ru.tbank.education.school.lesson3
//  products


data class Product(
    val name:String,
    var price:Int,
    var count:Int,
    ){

    override fun equals(x: Any?) : Boolean {
        if (x !is Product) return false
        return (x.name == name)
    }

    operator fun plus(a:Product):Product {
        if (equals(a.name)) {
            return Product(name, price, a.count + count)
        } else {
            return Product(name, price, a.count)
        }
    }

    operator fun minus(a:Product) : Product {
        if (equals(a.name)) {
            return Product(name, price, a.count - count)
        } else {
            return Product(name, price, a.count)
        }
}


abstract class Category(
    val name:String,
    var products:MutableList<Product>
) {
    fun findProducts (request : String) : List<Product>{
        if (name.contains((request))) {
            return products
        }
        return products.filter { it.name.contains(request) }
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