package ru.tbank.education.school.lesson3.accounting

abstract class Category(
    val name:String,
    var products:MutableList<Product>
){
    fun findProducts(a:String){
        if (a in this.name){
            var arr:List<String>
            for(i in this.products){
                print(i.name+" ")
            }
        }
        for(i in this.products){
            if(a in i.name){
                println(i.name)
            }
        }
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
        var ans:Int = 0
        for(i in this.products){
            ans += i.price * i.count
        }
        return ans
    }

    abstract fun sell(c: Int, ind: Int):Boolean
}