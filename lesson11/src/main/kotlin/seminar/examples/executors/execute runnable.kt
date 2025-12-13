package seminar.examples.executors

import java.util.concurrent.Executors

fun main() {

    val executor = Executors.newFixedThreadPool(4)

    val runnable = Runnable {
        println("Задача выполняется в ${Thread.currentThread().name}")
    }

    executor.execute(runnable)
}

