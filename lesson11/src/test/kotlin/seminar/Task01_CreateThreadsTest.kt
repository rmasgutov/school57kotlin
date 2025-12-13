package seminar

import org.junit.jupiter.api.Test
import seminar.tasks.CreateThreads
import kotlin.test.assertEquals

/**
 * Тесты для Task1: Создание потоков
 */
class CreateThreadsTest {

    @Test
    fun `should create 3 threads with correct names`() {
        val threads = CreateThreads.run()

        assertEquals(3, threads.size)
        assertEquals("Thread-A", threads[0].name)
        assertEquals("Thread-B", threads[1].name)
        assertEquals("Thread-C", threads[2].name)
    }

    @Test
    fun `all threads should be terminated after run`() {
        val threads = CreateThreads.run()

        threads.forEach { thread ->
            assertEquals(Thread.State.TERMINATED, thread.state)
        }
    }
}
