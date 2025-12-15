package seminar.tasks

import java.math.BigInteger
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Задание 6. Future
 *
 * Используя ExecutorService и Callable, параллельно вычислите факториалы чисел от 1 до 10.
 * Соберите результаты через Future.get().
 */
object FutureFactorial {

    /**
     * @return Map<Int, BigInteger> где ключ - число, значение - его факториал
     */
    fun run(): Map<Int, BigInteger> {
        val executor = Executors.newFixedThreadPool(4)

        val futures = (1..10).map { n ->
            n to executor.submit(Callable { factorial(n) })
        }

        val results = futures.associate { (n, future) ->
            n to future.get()
        }

        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.MINUTES)

        return results
    }

    /**
     * Вспомогательная функция для вычисления факториала
     */
    fun factorial(n: Int): BigInteger {
        if (n <= 1) return BigInteger.ONE
        return (2..n).fold(BigInteger.ONE) { acc, i ->
            acc * BigInteger.valueOf(i.toLong())
        }
    }
}
