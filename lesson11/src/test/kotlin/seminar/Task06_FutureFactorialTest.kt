package seminar

import org.junit.jupiter.api.Test
import seminar.tasks.FutureFactorial
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Тесты для Task6: Future
 */
class FutureFactorialTest {

    @Test
    fun `should calculate factorials correctly`() {
        val result = FutureFactorial.run()

        assertEquals(10, result.size)

        // Проверяем известные факториалы
        assertEquals(BigInteger.ONE, result[1])
        assertEquals(BigInteger.valueOf(2), result[2])
        assertEquals(BigInteger.valueOf(6), result[3])
        assertEquals(BigInteger.valueOf(24), result[4])
        assertEquals(BigInteger.valueOf(120), result[5])
        assertEquals(BigInteger.valueOf(720), result[6])
        assertEquals(BigInteger.valueOf(5040), result[7])
        assertEquals(BigInteger.valueOf(40320), result[8])
        assertEquals(BigInteger.valueOf(362880), result[9])
        assertEquals(BigInteger.valueOf(3628800), result[10])
    }

    @Test
    fun `factorial helper should work correctly`() {
        assertEquals(BigInteger.ONE, FutureFactorial.factorial(0))
        assertEquals(BigInteger.ONE, FutureFactorial.factorial(1))
        assertEquals(BigInteger.valueOf(120), FutureFactorial.factorial(5))
    }
}
