package homework

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ParallelTransformTest {

    @Test
    fun `should transform all elements`() = runBlocking {
        val items = listOf(1, 2, 3, 4, 5)

        val result = parallelTransform(items) { it * 2 }

        assertEquals(listOf(2, 4, 6, 8, 10), result)
    }

    @Test
    fun `should preserve order`() = runBlocking {
        val items = listOf("a", "b", "c", "d", "e")

        val result = parallelTransform(items) { it.uppercase() }

        assertEquals(listOf("A", "B", "C", "D", "E"), result)
    }

    @Test
    fun `should execute in parallel`() = runBlocking {
        val items = List(10) { it }

        val startTime = System.currentTimeMillis()

        val result = parallelTransform(items) {
            delay(100)
            it * 10
        }

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime

        // 10 элементов с delay(100) должны выполняться параллельно
        // Общее время должно быть ~100ms, а не 1000ms
        assertTrue(duration < 200)
        assertEquals(List(10) { it * 10 }, result)
    }

    @Test
    fun `should handle empty list`() = runBlocking {
        val result = parallelTransform(emptyList<Int>()) { it * 2 }

        assertTrue(result.isEmpty())
    }

    @Test
    fun `should work with suspend functions`() = runBlocking {
        val items = listOf(100, 200, 300)

        val result = parallelTransform(items) { value ->
            delay(value.toLong())
            value / 100
        }

        assertEquals(listOf(1, 2, 3), result)
    }
}