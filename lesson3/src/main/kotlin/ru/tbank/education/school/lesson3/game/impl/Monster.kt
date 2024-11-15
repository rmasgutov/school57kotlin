package ru.tbank.education.school.lesson3.game.impl

import ru.tbank.education.school.lesson3.game.Character
import kotlin.random.Random

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
class Monster(
    x: Int,
    y: Int,
    name: String,
) : Character(x, y, name, true) {

    constructor(name: String) : this(Random.nextInt(), Random.nextInt(), name)

}