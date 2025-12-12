package lection.coroutinebuilders

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    println("Начало программы")
    
    val result = runBlocking {
        delay(1000)
        "Результат из блокирующей корутины"
    }
    
    println(result)
    println("Конец программы")
}

