package ru.tbank.education.school.lesson3.lection.constructor

class InitOrderDemo(name: String) {

    val firstProperty = "Первое свойство: $name".also(::println)
    
    init {
        println("Первый блок инициализации: ${name}")
    }
    
    val secondProperty = "Второе свойство: ${name.length}".also(::println)
    
    init {
        println("Второй блок инициализации: ${name.length}")
    }

}

fun main() {
    val demo = InitOrderDemo("any_property")
}