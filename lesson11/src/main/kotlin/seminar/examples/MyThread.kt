package seminar.examples

class MyThread : Thread() {
    override fun run() {
        println("Привет из потока ${this.name}")
    }
}

fun main() {
    val thread = MyThread()
    thread.name = "MyThread-1"
    thread.start()
}

