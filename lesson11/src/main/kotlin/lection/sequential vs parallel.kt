package lection

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


fun main() = runBlocking {
    val dataList = List(1000) { it }

    val sequentialTime = measureTimeMillis {
        val result = dataList.map { processItem(it) }
        println("Последовательная обработка: обработано ${result.size} элементов")
    }

    val parallelTime = measureTimeMillis {
        val result = dataList.map { 
            async(Dispatchers.Default) { processItem(it) }
        }.awaitAll()
        println("Параллельная обработка: обработано ${result.size} элементов")
    }
    
    println("\nРезультаты:")
    println("Последовательная обработка: $sequentialTime мс")
    println("Параллельная обработка: $parallelTime мс")
    println("Ускорение в ${sequentialTime.toDouble() / parallelTime} раз")
}

// Имитация тяжелой вычислительной задачи
suspend fun processItem(item: Int): Int {
    delay(10)
    return item * 2
}