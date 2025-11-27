package ru.tbank.education.school.lesson8.lection.extensions

// Расширение свойства для String
val String.wordCount: Int
    get() = if (this.isBlank()) 0 else this.trim().split(Regex("\\s+")).size

// Расширение свойства для своего класса (только вычисление)
val Book.age: Int
    get() = java.time.Year.now().value - this.year

fun main() {
    println("Kotlin is concise".wordCount) // 3
    val b = Book("Clean Code", 2008)
    println("Book age: ${b.age}")
}
