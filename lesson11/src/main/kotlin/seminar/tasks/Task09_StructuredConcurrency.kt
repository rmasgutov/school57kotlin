package seminar.tasks

import kotlinx.coroutines.runBlocking

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
        TODO("Реализуйте structured concurrency с обработкой исключений")
    }
}
