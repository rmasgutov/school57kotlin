package homework

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger

/**
 * 
 * Задание: Исправьте гонку данных в этом классе любым из известных вам способов
 * 
 * Проблема: Несколько корутин одновременно увеличивают счетчик `value`,
 * что приводит к потере некоторых инкрементов из-за race condition.
 */
class UnsafeCounter {

    private var value = 0

    suspend fun increment() {
        delay(1)
        value++
    }

    fun getValue(): Int = value

    suspend fun runConcurrentIncrements(
        coroutineCount: Int = 10,
        incrementsPerCoroutine: Int = 1000
    ): Int = coroutineScope {

        val jobs = List(coroutineCount) {
            launch(Dispatchers.Default) {
                repeat(incrementsPerCoroutine) {
                    increment()
                }
            }
        }

        jobs.joinAll()

        getValue()
    }
}