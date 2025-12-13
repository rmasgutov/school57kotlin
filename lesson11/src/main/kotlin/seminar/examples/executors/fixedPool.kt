package seminar.examples.executors

import java.util.concurrent.Executors


fun main() {
    // 1. Фиксированный пул потоков
    val fixedPool = Executors.newFixedThreadPool(4)

    // 2. Кэширующий пул (создает потоки по необходимости)
    val cachedPool = Executors.newCachedThreadPool()

    // 3. Одиночный поток
    val singleThreadPool = Executors.newSingleThreadExecutor()

    // 4. Пул с планировщиком
    val scheduledPool = Executors.newScheduledThreadPool(4)
}




