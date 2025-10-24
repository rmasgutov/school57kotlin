package ru.tbank.education.school.lesson3.lection

import ru.tbank.education.school.lesson3.lection.inheritance.Bicycle
import ru.tbank.education.school.lesson3.lection.inheritance.Bus
import ru.tbank.education.school.lesson3.lection.inheritance.Car
import ru.tbank.education.school.lesson3.lection.inheritance.Transport

//open class Animal(val name: String) {
//
//    open fun speak() {
//        println("$name издаёт звук")
//    }
//
//}
//
//class Dog(name: String) : Animal(name) {
//
//    override fun speak() {
//        println("$name лает: Гав-гав!")
//    }
//
//}
//
//class Cat(name: String) : Animal(name) {
//
//    override fun speak() {
//        println("$name мяукает: Мяу!")
//    }
//}
//
//class Bird(name: String) : Animal(name) {
//
//    override fun speak() {
//        println("$name поёт: Чирик!")
//    }
//
//}
//
//fun main() {
//    val animals: List<Animal> = listOf(
//        Dog("Шарик"),
//        Cat("Маруся"),
//        Bird("Каркарыч")
//    )
//
//    for (t in animals) {
//        t.speak()   // у каждого свой вариант метода speak()
//    }
//}




// Базовый класс
open class Animal(val name: String) {
    open val sound: String = "какой-то звук"

    open fun speak() {
        println("$name издаёт звук: $sound")
    }
}

// Подкласс
class Dog(name: String) : Animal(name) {
    override val sound: String = "гав-гав"

    override fun speak() {
        super.speak()  // вызываем метод суперкласса
        println("$name ещё виляет хвостом")
    }
}

fun main() {
    val dog = Dog("Шарик")
    dog.speak()
}

