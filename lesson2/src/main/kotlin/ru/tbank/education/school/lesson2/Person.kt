package ru.tbank.education.school.lesson2

class Parent (
    val name: String
)

abstract class Person(
    var age: Int,
    val name: String,
    val gender: String,
    val parent: Parent
) {
    fun hello() {
        println("Hello $name")
    }

    fun birthday() {
        ++age
    }

    fun get_parent() {
        println(parent.name)
    }
}

class Programmer (age: Int, name: String, gender: String, parent: Parent,
    var level: String
) : Person(age, name, gender, parent) {
    fun changeLevel(newLevel : String) {
        level = newLevel
    }
}

fun main() {
    val programmer = Programmer(21, "Aleksey", "male", Parent("Lucy"), "junior")
    programmer.hello()
    println(programmer.level)
    programmer.changeLevel("senior")
    println(programmer.level)
}
