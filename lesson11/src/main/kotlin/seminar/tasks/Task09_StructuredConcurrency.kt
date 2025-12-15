package seminar.tasks

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

/**
 * Задание 10. Structured concurrency
 *
 * Создайте корутину, которая запускает 5 дочерних корутин.
 * Если одна из них падает с исключением, все остальные должны отмениться.
 */
object StructuredConcurrency {

    /**
     * @param failingCoroutineIndex индекс корутины, которая должна упасть (0-4)
     * @return количество корутин, которые успели завершиться до отмены
     */
    fun run(failingCoroutineIndex: Int): Int = runBlocking {
        val completedCount = AtomicInteger(0)

        try {
            withContext(NonCancellable) {
                repeat(5) { index ->
                    launch {
                        if (index == failingCoroutineIndex) {
                            delay(100)
                            throw RuntimeException("Coroutine $index failed!")
                        } else {
                            delay(200)
                            completedCount.incrementAndGet()
                            println("Coroutine $index completed")
                        }
                    }
                }
            }
        } catch (e: RuntimeException) {
            println("Caught exception: ${e.message}")
        }

        completedCount.get()
    }
}
