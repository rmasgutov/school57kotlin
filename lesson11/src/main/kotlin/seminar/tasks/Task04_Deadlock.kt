package seminar.tasks

/**
 * Задание 4. Deadlock
 *
 * Создайте пример deadlock с двумя ресурсами и двумя потоками.
 * Затем исправьте его.
 */
object Deadlock {

    /**
     * Демонстрация deadlock.
     * Внимание: этот метод зависнет! Используйте только для демонстрации.
     */
    fun runDeadlock() {
        val resourceA = Any()
        val resourceB = Any()

        val thread1 = Thread {
            synchronized(resourceA) {
                println("Thread1: holding A, waiting for B...")
                Thread.sleep(100)
                synchronized(resourceB) {
                    println("Thread1: got both!")
                }
            }
        }

        val thread2 = Thread {
            synchronized(resourceB) {
                println("Thread2: holding B, waiting for A...")
                Thread.sleep(100)
                synchronized(resourceA) {
                    println("Thread2: got both!")
                }
            }
        }

        thread1.start()
        thread2.start()

        // Это зависнет навсегда!
        thread1.join()
        thread2.join()
    }

    /**
     * Исправленная версия без deadlock.
     * Захватывайте ресурсы в одинаковом порядке.
     *
     * @return true если оба потока успешно завершились
     */
    fun runFixed(): Boolean {
        val resourceA = Any()
        val resourceB = Any()

        val thread1 = Thread {
            synchronized(resourceA) {
                println("Thread1: holding A...")
                Thread.sleep(100)
                synchronized(resourceB) {
                    println("Thread1: got both!")
                }
            }
        }

        val thread2 = Thread {
            // Захватываем в том же порядке: сначала A, потом B
            synchronized(resourceA) {
                println("Thread2: holding A...")
                Thread.sleep(100)
                synchronized(resourceB) {
                    println("Thread2: got both!")
                }
            }
        }

        thread1.start()
        thread2.start()

        thread1.join()
        thread2.join()

        return true
    }
}
