package ru.tbank.education.school.lesson3.game

abstract class Character (
    var x : Int,
    var y : Int,
    var name : String,
    val isBot : Boolean
) {
    open fun say() {
        println("Hi!")
    }
}

