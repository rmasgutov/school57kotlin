package ru.tbank.education.school.lesson3.RPGgame

class statblock(
    var hp: Float = 100F,
    var mana: Float = 100F,
    var stamina: Float = 100F,
    var lvl: Int = 1,
    var xp: Int = 0,
    var clasS: String = "None",
    var race: String = "Human",
    var power: Float = 10F
) {
    // Дополнительный конструктор
    constructor(savedData: String) : this(
        hp = savedData.split(",")[1].toFloat(),
        mana = savedData.split(",")[2].toFloat(),
        stamina = savedData.split(",")[3].toFloat(),
        lvl = savedData.split(",")[4].toInt(),
        xp = savedData.split(",")[5].toInt(),
        clasS = savedData.split(",")[6],
        race = savedData.split(",")[7],
        power = savedData.split(",")[8].toFloat()
    )

    // Кастомный геттер
    val isMaxLevel: Boolean
        get() = lvl >= 100

    fun levelUp() {
        lvl++
        hp += 10f
        power += 2f
        println("🎉 Уровень повышен! Теперь у вас $lvl уровень")
    }
}