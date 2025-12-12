package lection.coroutinebuilders

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val deferred: Deferred<Int> = async {
        delay(1000)
        42 // Возвращаемое значение
    }
    
    println("Выполняем другую работу...")
    val result = deferred.await() // Получаем результат
    println("Результат: $result")
}

