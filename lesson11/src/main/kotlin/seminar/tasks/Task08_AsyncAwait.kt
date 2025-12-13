package seminar.tasks

import kotlinx.coroutines.runBlocking

/**
 * Задание 9. async/await
 *
 * Используя async, параллельно вычислите сумму чисел от 1 до 1_000_000,
 * разбив на 4 части. Соберите результаты через await().
 */
object AsyncAwait {

    /**
     * @return сумма чисел от 1 до 1_000_000
     */
    fun run(): Long = runBlocking {
        TODO("Реализуйте параллельное суммирование с async/await")
    }
}
