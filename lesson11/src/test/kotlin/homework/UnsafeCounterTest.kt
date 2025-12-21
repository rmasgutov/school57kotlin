package homework

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnsafeCounterTest {

    @Test
    fun `should return correct value for 10 coroutines`() = runBlocking {
        val counter = UnsafeCounter()
        val expected = 10000
        
        val actual = counter.runConcurrentIncrements()
        
        assertEquals(expected, actual)
    }

    @Test
    fun `should return correct value for 2 coroutines`() = runBlocking {
        val counter = UnsafeCounter()
        val expected = 2000
        
        val actual = counter.runConcurrentIncrements(
            coroutineCount = 2,
            incrementsPerCoroutine = 1000
        )
        
        assertEquals(expected, actual)
    }

    @Test
    fun `should return correct value for 20 coroutines`() = runBlocking {
        val counter = UnsafeCounter()
        val expected = 2000
        
        val actual = counter.runConcurrentIncrements(
            coroutineCount = 20,
            incrementsPerCoroutine = 100
        )
        
        assertEquals(expected, actual)
    }

    @Test
    fun `should return correct value for 100 coroutines`() = runBlocking {
        val counter = UnsafeCounter()
        val expected = 10000
        
        val actual = counter.runConcurrentIncrements(
            coroutineCount = 100,
            incrementsPerCoroutine = 100
        )
        
        assertEquals(expected, actual)
    }

    @Test
    fun `should handle single coroutine correctly`() = runBlocking {
        val counter = UnsafeCounter()
        val expected = 5000
        
        val actual = counter.runConcurrentIncrements(
            coroutineCount = 1,
            incrementsPerCoroutine = 5000
        )
        
        assertEquals(expected, actual)
    }

    @Test
    fun `should be stable across multiple runs`() = runBlocking {
        repeat(5) {
            val counter = UnsafeCounter()
            val expected = 10000
            val actual = counter.runConcurrentIncrements()
            assertEquals(expected, actual)
        }
    }
}