package ru.tbank.education.school.lesson3.game

import kotlin.random.Random

class Monster(
        x: Int,
        y: Int,
        name: String,
) : Character(x, y, name, true) {

    constructor(name: String) : this(Random.nextInt(), Random.nextInt(), name)

}