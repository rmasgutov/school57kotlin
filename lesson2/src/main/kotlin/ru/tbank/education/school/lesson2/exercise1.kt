package ru.tbank.education.school.lesson2


enum class Color{
    RED, GREEN, BLACK
}

class Car(
        val model: String,
        var velocity: Int,
        var color: Color,
        val engine: Engine
) {
    fun drive() {
        println("car $model is driving")
        velocity += 100
    }
}
class Engine (
        val Litres: Int,
        model: String
)

class ElectricCar(
        model: String, velocity: Int, color: Color, engine: Engine,
        var charge: Int
) : Car(model, velocity, color, engine) {
    fun charging(power: Int) {
        charge += power
    }
}
class DieselCar(
        model: String, velocity: Int, color: Color, engine: Engine,
        var fuel: Int
) : Car(model, velocity, color, engine) {
    fun refueling(volume: Int) {
        fuel += volume
    }
}