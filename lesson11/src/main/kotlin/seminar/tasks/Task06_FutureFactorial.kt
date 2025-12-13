package seminar.tasks

import java.math.BigInteger
import java.util.concurrent.Executors
import java.util.concurrent.Future

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
        TODO("Реализуйте параллельное вычисление факториалов")
    }

    /**
     * Вспомогательная функция для вычисления факториала
     */
    fun factorial(n: Int): BigInteger {
        TODO("Реализуйте вычисление факториала")
    }
}
