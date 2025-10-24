package ru.tbank.education.school.lesson3.lection.inheritance

// Базовый класс
open class Transport(val brand: String) {
    open fun move() {
        println("$brand движется")
    }
}

// Наследник 1
class Car(brand: String, val fuel: Double) : Transport(brand) {
    override fun move() {
        println("Машина $brand едет по дороге. Остаток топлива: $fuel л.")
    }
}

// Наследник 2
class Bicycle(brand: String, val hasBell: Boolean) : Transport(brand) {
    override fun move() {
        val bell = if (hasBell) "с звонком" else "без звонка"
        println("Велосипед $brand едет по велодорожке $bell")
    }
}

// Наследник 3
class Bus(brand: String, val passengers: Int) : Transport(brand) {
    override fun move() {
        println("Автобус $brand везёт $passengers пассажиров")
    }
}

fun main() {
    val transports: List<Transport> = listOf(
        Car("Toyota", 30.0),
        Bicycle("Stels", true),
        Bus("Mercedes", 45)
    )

    for (t in transports) {
        t.move()   // у каждого свой вариант метода move()
    }
}

