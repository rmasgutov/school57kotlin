package seminar.tasks

import java.util.concurrent.CopyOnWriteArrayList
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
        val threadNames = CopyOnWriteArrayList<String>()
        val executor = Executors.newFixedThreadPool(4)

        repeat(20) { taskNum ->
            executor.submit {
                val threadName = Thread.currentThread().name
                println("Task $taskNum executed by $threadName")
                threadNames.add(threadName)
                Thread.sleep(200)
            }
        }

        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.MINUTES)

        return threadNames.toList()
    }
}
