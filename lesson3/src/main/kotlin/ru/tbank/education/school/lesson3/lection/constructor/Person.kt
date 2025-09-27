package ru.tbank.education.school.lesson3.lection.constructor

open class Person (val name: String, var age: Int, val job: String = "") {

    init {
        println("Создан объект Person: $name, возраст $age")
    }
}


fun main() {
    val p = Person("Аня", 16)
}