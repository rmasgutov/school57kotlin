package ru.tbank.education.school.lesson8.lection.extensions

class User(val name: String) {
    companion object {
        fun of(name: String): User = User(name)
    }
}

// Расширение для companion:
fun User.Companion.guest(): User = User("Guest")

// Расширение для companion с параметром:
fun User.Companion.fromId(id: Int): User = User("User#$id")

fun main() {
    val u1 = User.of("Alice")
    val u2 = User.guest()
    val u3 = User.fromId(42)
    println("${u1.name}, ${u2.name}, ${u3.name}")
}
