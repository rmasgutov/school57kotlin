package ru.tbank.education.school.lesson3.lection.oop

class Student(val name: String, val grades: List<Int>) {

    fun averageGrade(): Double {
        return grades.average()
    }

    fun printInfo() {
        println("Ученик: $name")
        println("Оценки: $grades")
        println("Средний балл: ${averageGrade()}")
    }

}