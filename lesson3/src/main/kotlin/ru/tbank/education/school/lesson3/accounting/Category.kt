package ru.tbank.education.school.lesson3.accounting

abstract class Category(
    val name:String,
    var products:MutableList<Product>
){
    fun findProducts(a:String): List<Product>{
        if (name.contains((a))) {
            return products
        }
        return products.filter { it.name.contains(a) }
    }
    fun delete(ind:Int){
        this.products.removeAt(ind)
    }
    fun topUp(p:Product){
        this.products.add(p)
        this.inventoryManagement()
    }
    fun inventoryManagement(){
        var i:Int = 0
        var j:Int = 0
        while(i< this.products.size){
            j = i+1
            while (j < this.products.size && this.products[j] != this.products[i]){
                j++
            }
            if (j != this.products.size){
                this.products[i].count += this.products[j].count
                this.products.removeAt(j)
            }
            i += 1
        }
    }
    fun sum():Int{
        val ans:Int = products.sumOf{it.price*it.count}
        return ans
    }

    abstract fun sell(c: Int, ind: Int):Boolean
}