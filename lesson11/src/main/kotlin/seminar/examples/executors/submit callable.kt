package seminar.examples.executors

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

fun main() {
    val executor = Executors.newFixedThreadPool(4)

    val runnable = Runnable {
        println("Задача выполняется в ${Thread.currentThread().name}")
    }

    val future1: Future<*> = executor.submit(runnable)

    val callable = Callable {
        Thread.sleep(1000)
        "Результат задачи"
    }

    val future2: Future<String> = executor.submit(callable)

    future1.get()
    val result = future2.get()
    executor.shutdown()
}