package seminar

import org.junit.jupiter.api.Test
import seminar.tasks.AsyncAwait
import kotlin.test.assertEquals

/**
 * Тесты для Task9: async/await
 */
class AsyncAwaitTest {

    @Test
    fun `should calculate sum of numbers from 1 to 1_000_000`() {
        val result = AsyncAwait.run()

        // Сумма чисел от 1 до n = n * (n + 1) / 2
        val expected = 1_000_000L * 1_000_001L / 2
        assertEquals(expected, result)
    }
}
