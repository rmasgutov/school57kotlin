package ru.tbank.education.school.lesson7.lection

data class User(var name: String = "", var age: Int = 0)

fun main() {
    val user1 = User().apply {
        name = "Анна"
        age = 25
    }
    println(user1) // User(name=Анна, age=25)

    val user2 = User(name = "Борис", age = 30).also {
        println("Создан пользователь: $it")
    }.also {
        println("Имя в верхнем регистре: ${it.name.uppercase()}")
    }

    println(user2) // User(name=Борис, age=30)

    val name: String? = "Галина"

    val length = name?.let {
        println("Имя: $it")
        it.length
    }

    println("Длина имени: $length") // Длина имени: 6

    val person = User(name = "Денис", age = 28)

    val description = with(person) {
        "Имя: $name, возраст: $age"
    }

    println(description) // Имя: Денис, возраст: 28
}