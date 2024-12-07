package ru.tbank.education.school.lesson2

import kotlin.math.sqrt
import kotlin.reflect.KProperty

class Engine(
    var volumeLitres: Int,
    var model: String
) {
    fun start() {
        println("Engine started!")
    }
    fun stop() {
        println("Engine stopped!")
    }
}

enum class Color {
    RED, BLUE, GREEN
}

open class Car(
    protected var model: String,
    protected var velosity: Int,
    protected var color: Color,
    protected var engine: Engine
) {
    open fun drive() {
        engine.start()
        println("Car $model is driving!")
        velosity += 10
    }
}

class PassangerCar(
    model: String, velosity: Int, color: Color, engine: Engine,
    protected var avalibleSeats: Int
) : Car(model, velosity, color, engine) {
    fun seat(passangerCount: Int) {
        avalibleSeats -= passangerCount
    }

    override fun drive() {
        println("PassangerCar $model is driving!")

    }
}

class Truck(
    model: String, velosity: Int, color: Color, engine: Engine,
    protected var avalibleSlots: Int
) : Car(model, velosity, color, engine) {
    fun load(goodsCount: Int) {
        avalibleSlots -= goodsCount
    }
}


abstract class AbstractCar(val model: String) {
    abstract fun drive()
}

class PassangerCar2(model: String): AbstractCar(model) {
    override fun drive() {
        println("PassangerCar $model is driving!")
    }
}

fun main() {
    var a = Car("a", 0, Color.RED, Engine(2, "1"))
}