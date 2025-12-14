package homework

import VisibilityProblem
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class VisibilityProblemTest {

    @Test
    fun `should complete without timeout when fixed`() {
        val problem = VisibilityProblem()

        val writer = problem.startWriter()
        val reader = problem.startReader()

        writer.start()
        reader.start()

        // Ждем завершения writer
        writer.join(2000)
        assertFalse(writer.isAlive, "Writer должен завершиться")

        // Ждем завершения reader с таймаутом
        reader.join(2000)
        assertFalse(reader.isAlive, "Reader должен завершиться за 2 секунды")
    }

    @Test
    fun `multiple runs should always succeed when fixed`() {
        repeat(5) {
            val problem = VisibilityProblem()

            val writer = problem.startWriter()
            val reader = problem.startReader()

            writer.start()
            reader.start()

            writer.join()

            // Даем reader время завершиться
            Thread.sleep(100)

            // Пытаемся дождаться
            reader.join(100)

            assertFalse(reader.isAlive, "Reader должен завершиться (запуск $it)")
        }
    }


    @Test
    fun `writer should finish quickly`() {
        val problem = VisibilityProblem()
        val writer = problem.startWriter()

        writer.start()
        writer.join(2000)

        assertFalse(writer.isAlive, "Writer должен быстро завершиться")
    }
}