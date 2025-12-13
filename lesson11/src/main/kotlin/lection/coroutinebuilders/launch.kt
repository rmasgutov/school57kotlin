package lection.coroutinebuilders

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        // Код, выполняемый в корутине
        delay(1000)
        println("Корутина завершена!")
    }
    println("Основной поток продолжает работу")
    job.join() // Ждем завершения корутины
}

