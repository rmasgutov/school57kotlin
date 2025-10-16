package ru.tbank.education.school.lesson7.lection

fun main() {
    // Ссылка на метод String.plus — функция принимает две строки и возвращает их конкатенацию
    val stringPlus: (String, String) -> String = String::plus

    // Ссылка на метод Int.plus — функция расширения: Int.(Int) -> Int
    val intPlus: Int.(Int) -> Int = Int::plus

    // Вызов через invoke()
    println(stringPlus.invoke("<-", "->"))      // <->
    println(stringPlus("Hello, ", "world!"))    // Hello, world!

    // Разные способы вызова функции Int.(Int) -> Int
    println(intPlus.invoke(1, 1))   // 2
    println(intPlus(1, 2))          // 3
    println(2.intPlus(3))           // 5
}
