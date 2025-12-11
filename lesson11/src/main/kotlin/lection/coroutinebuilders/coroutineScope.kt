package lection.coroutinebuilders

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Родитель начал работу")

    launch {
        delay(500)
        println("Ребенок 1 завершил")
    }

    launch {
        delay(1000)
        println("Ребенок 2 завершил")
    }

    println("Родитель ждет детей...")
}

