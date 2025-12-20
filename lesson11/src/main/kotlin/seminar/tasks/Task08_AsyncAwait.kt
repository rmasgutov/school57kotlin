package seminar.tasks

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

/**
 * Задание 9. async/await
 *
 * Используя async, параллельно вычислите сумму чисел от 1 до 1_000_000,
 * разбив на 4 части. Соберите результаты через await().
 */
object AsyncAwait {

    /**
     * @return сумма чисел от 1 до 1_000_000
     */
    fun run(): Long = runBlocking {
        val ranges = listOf(
            1L..250_000L,
            250_001L..500_000L,
            500_001L..750_000L,
            750_001L..1_000_000L
        )

        val deferreds = ranges.map { range ->
            async {
                range.sum()
            }
        }

        deferreds.awaitAll().sum()
    }
}
