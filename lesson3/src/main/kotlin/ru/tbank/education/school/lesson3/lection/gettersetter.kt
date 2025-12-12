package ru.tbank.education.school.lesson3.lection

class StudentDefault(val name: String, val grade: Int)

class Student(val name: String) {

    var grade: Int = 1
        // геттер – вызывается при обращении к свойству
        get() {
            println("Запросили значение grade")
            return field
        }

        // сеттер – вызывается при изменении свойства
        set(value) {
            if (value in 1..11) {
                field = value
                println("Установлен класс: $value")
            } else {
                println("Ошибка: класс должен быть от 1 до 11")
            }
        }
}

fun main() {
    val student = Student("Аня")

    student.grade
    student.grade = 5
    student.grade = 20

    println("--------------")

    val defaultStudent = StudentDefault("Аня", 5)
    println(defaultStudent.name)
    println(defaultStudent.grade)
}