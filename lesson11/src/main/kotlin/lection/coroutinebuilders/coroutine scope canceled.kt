package lection.coroutinebuilders

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val parentJob = launch {
        val child1 = launch {
            repeat(10) { i ->
                delay(500)
                println("Child 1: $i")
            }
        }

        val child2 = launch {
            repeat(10) { i ->
                delay(300)
                println("Child 2: $i")
            }
        }
    }
    
    delay(1500)
    println("Отменяем родителя!")
    parentJob.cancel()
}