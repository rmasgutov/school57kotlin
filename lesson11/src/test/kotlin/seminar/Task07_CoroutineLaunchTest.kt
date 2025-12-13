package seminar

import org.junit.jupiter.api.Test
import seminar.tasks.CoroutineLaunch
import kotlin.test.assertEquals

/**
 * Тесты для Task8: Первая корутина
 */
class CoroutineLaunchTest {

    @Test
    fun `should launch 3 coroutines with correct names`() {
        val names = CoroutineLaunch.run()

        assertEquals(3, names.size)
        assertEquals("Coroutine-A", names[0])
        assertEquals("Coroutine-B", names[1])
        assertEquals("Coroutine-C", names[2])
    }
}
