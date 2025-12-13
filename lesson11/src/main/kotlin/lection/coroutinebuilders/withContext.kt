package lection.coroutinebuilders

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

suspend fun loadData(): String = withContext(Dispatchers.IO) {
    delay(1000)
    "Данные загружены"
}

fun main() = runBlocking {
    val result = loadData()
    println(result)
}

