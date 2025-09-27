package ru.tbank.education.school.lesson3.lection.constructor

open class Base(val name: String) {

    init { 
        println("Инициализация класса Base") 
    }

    open val size: Int =
        name.length.also { 
            println("Инициализация свойства size в классе Base: $it") 
        }
}

class Derived(
    name: String,
    val lastName: String
) : Base(name.replaceFirstChar { it.uppercase() }
        .also { println("Аргументы конструктора Base: $it") }) {

    init { 
        println("Инициализация класса Derived") 
    }

    override val size: Int =
        (super.size + lastName.length).also { 
            println("Инициализация size в классе Derived: $it") 
        }
}

fun main() {
    println("Построение класса Derived(\"hello\", \"world\")")
    Derived("hello", "world")
}
