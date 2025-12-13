package seminar.tasks

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Задание 5. ExecutorService
 *
 * Используя Executors.newFixedThreadPool(4), выполните 20 задач.
 * Каждая задача выводит свой номер и имя потока, затем спит 200мс.
 */
object ExecutorServiceExample {

    /**
     * @return список имён потоков, которые выполняли задачи
     */
    fun run(): List<String> {
        val executor = Executors.newFixedThreadPool(4)
        val threads = mutableListOf<String>()
        repeat(20) { taskNum ->
            executor.submit {
                println(taskNum)
                println(Thread.currentThread().name)
                threads.add(Thread.currentThread().name)
            }
        }

        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.MINUTES)

        return threads
    }
}
