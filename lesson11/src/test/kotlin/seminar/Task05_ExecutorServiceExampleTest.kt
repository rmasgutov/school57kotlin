package seminar

import org.junit.jupiter.api.Test
import seminar.tasks.ExecutorServiceExample
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Тесты для Task5: ExecutorService
 */
class ExecutorServiceExampleTest {

    @Test
    fun `should execute 20 tasks using 4 threads`() {
        val threadNames = ExecutorServiceExample.run()

        // Должно быть 20 записей (по одной на каждую задачу)
        assertEquals(20, threadNames.size)

        // Все имена должны содержать "pool" (стандартное именование потоков ExecutorService)
        threadNames.forEach { name ->
            assertTrue(name.contains("pool"), "Thread name should contain 'pool': $name")
        }

        // Уникальных потоков должно быть не больше 4
        val uniqueThreads = threadNames.toSet()
        assertTrue(uniqueThreads.size <= 4, "Should use at most 4 threads")
    }
}
