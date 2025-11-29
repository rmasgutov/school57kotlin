package ru.tbank.education.school.lesson7.lection

fun main() {
    // Функция с параметрами и возвращаемым значением
    val combine: (Int, Int) -> String = { a, b ->
        "Сумма: ${a + b}"
    }

    // Функция без параметров
    val greet: () -> String = {
        "Привет, Kotlin!"
    }

    // Функция, ничего не возвращающая (Unit)
    val sayHello: () -> Unit = {
        println("Hello!")
    }

    // Функция с именованными параметрами в типе
    val distance: (x1: Int, x2: Int) -> Int = { x1, x2 ->
        kotlin.math.abs(x1 - x2)
    }

    // Nullable-функция
    var sum: ((Int, Int) -> Int)? = null
    sum = { a, b -> a + b }

    // Функция, возвращающая другую функцию
    val multiplier: (Int) -> ((Int) -> Int) = { factor ->
        { number -> number * factor }
    }
}