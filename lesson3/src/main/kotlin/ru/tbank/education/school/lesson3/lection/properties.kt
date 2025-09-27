package ru.tbank.education.school.lesson3.lection

class Car(
    val model: String,        // неизменяемое свойство (val)
    var fuel: Double,          // изменяемое свойство (var)
) {
    // вычисляемое свойство (нет хранения, значение считается при каждом вызове)
    val isEmpty: Boolean
        get() = fuel <= 0


    // свойство с кастомным сеттером
    var speed: Int = 0
        set(value) {
            if (value >= 0) field = value
            else println("Скорость не может быть отрицательной!")
        }

    // lateinit — отложенная инициализация (например, владелец машины)
    lateinit var owner: String

    // едем!
    fun drive() {
        if (fuel > 0) {
            println("$model едет со скоростью $speed км/ч")
            fuel -= 1
        } else {
            println("$model не может ехать — нет топлива!")
        }
    }
}

fun main() {
    val car = Car("Toyota", 5.0)

    // val → нельзя изменить
    println(car.model)      // Toyota
    // car.model = "BMW" ❌ Ошибка

    // var → можно менять
    car.fuel = 10.0
    println("Топливо: ${car.fuel}")

    // вычисляемое свойство
    println("Пустой бак? ${car.isEmpty}")

    // сеттер с проверкой
    car.speed = 60
    car.drive()
    car.speed = -10         // ❌ Ошибка (проверка в сеттере)

    // lateinit → инициализация позже
    car.owner = "Аня"
    println("Владелец: ${car.owner}")
}

