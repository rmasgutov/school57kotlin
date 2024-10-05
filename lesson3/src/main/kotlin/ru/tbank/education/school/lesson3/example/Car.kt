package ru.tbank.education.school.lesson3.example

/**
 * Возможные цвета машин.
 */
enum class Color {
    RED, BLUE, GREEN
}

/**
 * Двигатель машины.
 */
class Engine(
    var volumeLitres: Int,
    val model: String
) {
    fun start() {
        println("Engine started")
    }

    fun stop() {
        println("Engine stopped")
    }
}

/**
 * Машина.
 */
open class Car(
    val model: String,
    var velocity: Int,
    var color: Color,
    var engine: Engine
) {
    /**
     * Поехать.
     */
    open fun drive() {
        engine.start()
        println("Car $model is driving")
        velocity += 10
    }

    var velocityMph: Double
        get() = velocity * 1.6
        set(value) {
            velocity = (value / 1.6).toInt()
        }

}

/**
 * Легковой автомобиль.
 */
class PassengerCar(
    model: String, velocity: Int, color: Color, engine: Engine,
    var availableSeats: Int
) : Car(model, velocity, color, engine) {

    /**
     * Поехать.
     */
    override fun drive() {
        println("PassengerCar $model is driving")
    }

    /**
     * Метод для посадки пассажиров.
     * Изменяет число доступных мест.
     */
    fun seat(passengerCount: Int) {
        availableSeats -= passengerCount
    }
}

/**
 * Грузовой автомобиль.
 */
class Truck(
    model: String, velocity: Int, color: Color, engine: Engine,
    var availableSlots: Int
) : Car(model, velocity, color, engine) {

    /**
     * Метод для загрузки товарами.
     * Изменяет число доступных слотов для товаров.
     */
    fun load(goodsCount: Int) {
        availableSlots -= goodsCount
    }
}

/**
 * Пример создания экземпляра машины.
 */
fun main() {
    val car = PassengerCar("Lada", 0, Color.RED, Engine(2, "V6"), 5)
    car.drive()
    println("Model: ${car.model}")
    println("Velocity: ${car.velocity}")
    println("Velocity MPH: ${car.velocityMph}")
    println("Color: ${car.color}")

    println("car.toString(): ${car.toString()}")


    val carData = CarData("Lada", 0, Color.RED, Engine(2, "V6"))
    println("carData.toString(): ${carData.toString()}")
}

/**
 * Класс абстрактной машины.
 */
abstract class AbstractCar(val model: String) {
    /**
     * Метод движения машины.
     */
    abstract fun drive()
}

/**
 * Легковая машина от абстрактной машины.
 */
class PassengerCar2(model: String) : AbstractCar(model) {
    /**
     * Реализация метода движения.
     */
    override fun drive() {
        println("PassengerCar $model is driving")
    }
}

/**
 * Машина. Дата класс.
 */
data class CarData(
    val model: String,
    var velocity: Int,
    var color: Color,
    var engine: Engine
)


