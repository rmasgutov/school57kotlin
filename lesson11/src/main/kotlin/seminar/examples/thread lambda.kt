package seminar.examples

fun main() {
    val thread = Thread {
        println("Привет из лямбды")
    }
    thread.start()
}


