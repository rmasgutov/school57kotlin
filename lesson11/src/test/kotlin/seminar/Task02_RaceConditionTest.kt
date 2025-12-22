package seminar

import org.junit.jupiter.api.Test
import seminar.tasks.RaceCondition
import kotlin.test.assertTrue

/**
 * Тесты для Task2: Race condition
 */
class RaceConditionTest {

    @Test
    fun `should demonstrate race condition - result may be less than 10000`() {
        // Запускаем несколько раз чтобы увидеть race condition
        val results = (1..5).map { RaceCondition.run() }

        // Хотя бы один результат должен быть меньше 10000 (или все если повезло)
        // Но все результаты должны быть > 0
        results.forEach { result ->
            assertTrue(result > 0, "Counter should be positive")
            assertTrue(result <= 10000, "Counter should not exceed 10000")
        }

        println("Race condition results: $results")
    }
}
