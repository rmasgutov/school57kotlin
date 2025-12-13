package seminar

import org.junit.jupiter.api.Test
import seminar.tasks.StructuredConcurrency
import kotlin.test.assertTrue

/**
 * Тесты для Task10: Structured concurrency
 */
class StructuredConcurrencyTest {

    @Test
    fun `should cancel sibling coroutines when one fails`() {
        // Корутина с индексом 2 падает
        val completedCount = StructuredConcurrency.run(failingCoroutineIndex = 2)

        // Не все корутины должны успеть завершиться
        assertTrue(completedCount < 5, "Some coroutines should be cancelled")
    }

    @Test
    fun `first coroutine fails should cancel all others`() {
        val completedCount = StructuredConcurrency.run(failingCoroutineIndex = 0)

        // Почти никто не должен успеть завершиться
        assertTrue(completedCount <= 1, "Most coroutines should be cancelled")
    }
}
