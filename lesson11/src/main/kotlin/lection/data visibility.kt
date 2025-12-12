package lection

import kotlinx.coroutines.*

@Volatile
var nextCustomerNumber = 0

fun main() = runBlocking {

    val controllerJob = launch(Dispatchers.Default) {
        println("Контроллер: жду клиента #4...")

        while (nextCustomerNumber < 4) {
        }

        println("Контроллер: клиент #$nextCustomerNumber наконец дошел!")
    }

    val queueJob = launch(Dispatchers.Default) {
        for (i in 0..20) {
            delay(200)
            println("Очередь: вызываю клиента #$nextCustomerNumber")
            nextCustomerNumber++
        }
        println("Очередь: все клиенты вызваны!")
    }

    controllerJob.join()
    queueJob.join()
}