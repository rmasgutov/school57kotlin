package ru.tbank.education.school.lesson3.game

abstract class Character(
    var x: Float,
    var y: Float,
    var name: String,
    var isBot: Boolean
) {
    open fun say() {
        println("Hi!")
    }
}