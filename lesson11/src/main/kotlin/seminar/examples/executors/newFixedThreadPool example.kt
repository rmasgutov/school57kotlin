package seminar.examples.executors

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

fun main() {
    val executor = Executors.newFixedThreadPool(4)
    val tasks = List(20) { taskNumber ->
        Callable<String> {
            println("Задача $taskNumber в ${Thread.currentThread().name}")
            Thread.sleep(200)
            "Результат $taskNumber"
        }
    }

    val results: List<Future<String>> = executor.invokeAll(tasks)

    executor.shutdown()
}
