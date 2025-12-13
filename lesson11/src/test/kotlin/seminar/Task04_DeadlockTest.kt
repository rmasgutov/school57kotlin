package seminar

import org.junit.jupiter.api.Test
import seminar.tasks.Deadlock
import kotlin.test.assertTrue

/**
 * Тесты для Task4: Deadlock
 */
class DeadlockTest {

//     Тест для deadlock закомментирован - он зависнет!
//     @Test
//     fun `should demonstrate deadlock`() {
//         Deadlock.runDeadlock()
//     }

    @Test
    fun `fixed version should complete without deadlock`() {
        val result = Deadlock.runFixed()
        assertTrue(result, "Fixed version should complete successfully")
    }
}
