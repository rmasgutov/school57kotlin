package seminar.tasks

/**
 * Задание 2. Race condition
 *
 * Создайте переменную counter = 0.
 * Запустите 10 потоков, каждый из которых увеличивает counter на 1000.
 * Выведите финальное значение и объясните результат.
 */
object RaceCondition {

    /**
     * @return финальное значение counter (может быть меньше 10000 из-за race condition)
     */
    fun run(): Int {
        var counter = 0

        val threads = (1..10).map {
            Thread {
                repeat(1000) {
                    counter++ // Не атомарная операция - race condition!
                }
            }
        }

        threads.forEach { it.start() }
        threads.forEach { it.join() }

        println("Task2 result: $counter (expected 10000)")
        return counter
    }
}
