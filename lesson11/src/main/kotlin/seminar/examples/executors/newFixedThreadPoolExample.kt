package seminar.examples.executors

import java.util.concurrent.Executors
import java.util.concurrent.Future

fun main() {
    val executor = Executors.newFixedThreadPool(4)
    val futures = mutableListOf<Future<*>>()

    for (i in 1..20) {
        val future = executor.submit {
            println("Задача $i выполняется в ${Thread.currentThread().name}")
            Thread.sleep(200)
        }
        futures.add(future)
    }

    executor.shutdown()
}
