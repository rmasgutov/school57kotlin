package ru.tbank.education.school.lesson7.lection.collections

data class Student(val name: String, val group: String)

fun main() {
    val students = listOf(
        Student(name = "Анна", group = "A-01"),
        Student(name = "Борис", group = "A-02"),
        Student(name = "Виктор", group = "A-01"),
        Student(name = "Галина", group = "A-03"),
        Student(name = "Денис", group = "A-02")
    )

    val byGroup = students.groupBy { it.group }

    byGroup.forEach { (group, members) ->
        println("Группа $group: ${members.joinToString { it.name }}")
    }
}
