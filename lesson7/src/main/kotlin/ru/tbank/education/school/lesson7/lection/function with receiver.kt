package ru.tbank.education.school.lesson7.lection

// Обобщённое расширение configure для любого типа T
fun <T> T.configure(block: T.() -> Unit): T {
    block()
    return this  // возвращаем исходный объект
}

fun main() {
    val user = User().configure {
        name = "Анна"
        age = 25
    }

    println(user)
}
