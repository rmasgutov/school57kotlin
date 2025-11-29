package ru.tbank.education.school.lesson3.lection.constructor

class Book(val title: String) {
    var pages: Int = 0

    constructor(title: String, pages: Int) : this(title) {
        this.pages = pages
        println("Создана книга \"$title\" на $pages страниц")
    }
}


fun main() {
    val b1 = Book("Математика")
    val b2 = Book("Физика", 250)
}