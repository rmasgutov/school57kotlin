package seminar.examples

fun main() {
    val runnable = Runnable {
        println("Привет из Runnable")
    }

    val thread = Thread(runnable)
    thread.name = "RunnableThread"
    thread.start()
}



