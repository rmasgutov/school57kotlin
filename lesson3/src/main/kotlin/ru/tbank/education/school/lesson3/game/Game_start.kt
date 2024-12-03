package ru.tbank.education.school.lesson3.game

fun main() {
    val hero = Hero("Hero")
    val monster = Monster("Geroin")

    hero.say()
    monster.say()

    hero.move(10, 20)
    monster.move(15, 25)

    hero.hasBoots = false
    hero.move(10, 20)
    hero.x = 0
    hero.move(0, 20)
}
