package ru.tbank.education.school.lesson3.accounting

data class Product(
    val name:String,
    val price:Int,
    var count:Int){
    fun equals(a:Product):Boolean{
        return (this.name == a.name)
    }
    operator fun plus(a:Product):Product{
        return Product(this.name, this.price, this.count+a.count)
    }
}