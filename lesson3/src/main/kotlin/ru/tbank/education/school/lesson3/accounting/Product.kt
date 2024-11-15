package ru.tbank.education.school.lesson3.accounting
import java.io.IOException

data class Product(
    val name:String,
    val price:Int,
    var count:Int){
    override fun equals(a: Any?): Boolean {
        if (a !is Product) return false
        return name == a.name
    }
    operator fun plus(a:Product):Product{
        if(name == a.name){
        return Product(name, price, count+a.count)}
        else{
            throw IOException("error")
        }
    }
}