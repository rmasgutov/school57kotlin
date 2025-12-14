package seminar.tasks

/**
 * Задание 1. Создание потоков
 *
 * Создайте 3 потока с именами "Thread-A", "Thread-B", "Thread-C".
 * Каждый поток должен вывести своё имя 5 раз с задержкой 500мс.
 */
object CreateThreads {

    /**
     * @return список созданных потоков (после их завершения)
     */
    fun run(): List<Thread> {
        val threadNames = listOf("Thread-A", "Thread-B", "Thread-C")

        val threads = threadNames.map { name ->
            Thread {
                println(Thread.currentThread().name)
                repeat(5) {
                    println("Hello from $name")
                    Thread.sleep(500)
                }
            }.apply { this.name = name }
        }

        threads.forEach { it.start() }
        threads.forEach { it.join() }

        return threads
    }
}
