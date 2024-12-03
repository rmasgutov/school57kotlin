package ru.tbank.education.school.lesson2

enum class Specifications {

    first, second, therd
}

open class Weapon (
    val name : String,
    var endurance : Int,
    var specifications : Specifications
) {
    open fun use() {
        println("$name used")
        endurance -= 1
    }
}
class Aaa(
    name : String, endurance : Int, specifications : Specifications
) : Weapon(name, endurance, specifications){
    override fun use() {
        println("$name used")

    }
}


fun main(){
    val gun = Weapon("gun" , 100, Specifications.first)
    gun.use()
    println(gun.specifications)
    var n = 1

}