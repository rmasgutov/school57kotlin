package seminar

import org.junit.jupiter.api.Test
import seminar.tasks.SynchronizedCounter
import kotlin.test.assertEquals

/**
 * Тесты для Task3: Synchronized
 */
class SynchronizedCounterTest {

    @Test
    fun `should always return exactly 10000 with synchronized`() {
        // Запускаем несколько раз чтобы убедиться в стабильности
        repeat(5) {
            val result = SynchronizedCounter.run()
            assertEquals(10000, result, "Synchronized counter should always be 10000")
        }
    }
}
