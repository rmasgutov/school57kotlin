package ru.tbank.education.school.lesson3.game

abstract class Character {
    var x: Double = 0.0
    var y: Double = 0.0

    var name: String = ""

    abstract val isBot: Boolean

    open fun say() {
        println("Hi!")
    }
}
