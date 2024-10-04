package ru.tbank.education.school.lesson3.accounting

class FoodCategory(name:String, products:MutableList<Product>):Category(name, products){
    override fun sell(c:Int, ind:Int):Boolean{
        if(this.products[ind].count>c*2){
            this.products[ind].count -= c*2
        }
        else{
            this.products[ind].count = 0
            return true
        }
        return false
    }
}