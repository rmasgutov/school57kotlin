package seminar.tasks

/**
 * Задание 3. Synchronized
 *
 * Исправьте задание 2 с помощью @Synchronized или synchronized {} блока,
 * чтобы результат всегда был 10000.
 */
object SynchronizedCounter {

    /**
     * @return финальное значение counter (должно быть ровно 10000)
     */
    fun run(): Int {
        var counter = 0
        val lock = Any()

        val threads = (1..10).map {
            Thread {
                repeat(1000) {
                    synchronized(lock) {
                        counter++
                    }
                }
            }
        }

        threads.forEach { it.start() }
        threads.forEach { it.join() }

        println("Task3 result: $counter")
        return counter
    }
}
