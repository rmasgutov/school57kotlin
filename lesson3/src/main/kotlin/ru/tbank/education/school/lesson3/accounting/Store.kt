package ru.tbank.education.school.lesson3.accounting


class Store(
    var sales:MutableList<Product>,
    var warehouse:MutableList<Category>){
    fun topUp(c: Category){
        warehouse.add(c)
    }
    fun sell(p:Product, c:Int){
        val ind:Pair<Int, Int> = this.search(p.name)
        if (ind != Pair(-1, -1)){
            if (this.warehouse[ind.first].sell(c, ind.second)){
                this.sales.add(p)
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