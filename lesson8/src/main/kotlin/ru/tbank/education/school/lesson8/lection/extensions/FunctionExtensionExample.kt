package ru.tbank.education.school.lesson8.lection.extensions

// Свой класс
data class Book(val title: String, val year: Int)

// Расширение: определить, «классика» ли книга
fun Book.isClassic(): Boolean = this.year < 1970

// Расширение стандартного класса String
fun String.words(): List<String> =
    this.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }

// Расширение для nullable-рецептора String?
fun String?.isNullOrBlankFancy(): Boolean = this == null || this.isBlank()

// Демонстрация статического разрешения расширений:
open class Animal
class Cat : Animal()

fun Animal.kind(): String = "Animal"
fun Cat.kind(): String = "Cat"

fun main() {
    val b = Book("The Hobbit", 1937)
    println("${b.title} classic? ${b.isClassic()}") // true

    val s = "  Kotlin is fun  "
    println(s.words()) // ["Kotlin", "is", "fun"]

    val ns: String? = null
    val ks: String? = "   "
    println(ns.isNullOrBlankFancy()) // true
    println(ks.isNullOrBlankFancy()) // true

    // Важная особенность: статическое разрешение по типу переменной
    val a: Animal = Cat()
    println(a.kind())
    // Если явно указать тип Cat:
    val c: Cat = Cat()
    println(c.kind())
}
