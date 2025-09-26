package ru.tbank.education.school.lesson3.seminar.university

import Lecturer
import ru.tbank.education.school.lesson3.seminar.bank.models.Group
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.Course
import ru.tbank.education.school.lesson3.seminar.university.people.Student
import ru.tbank.education.school.lesson3.seminar.university.people.User
import ru.tbank.education.school.lesson3.seminar.university.sealedclasses.CourseMaterial
import java.time.LocalDateTime

fun main() {
    val groupA = Group("1", "A")
    val groupB = Group("2", "B")

    val st1 = Student("1", "Савелий", "saveliy@t-bank.t", 2000)
    val st2 = Student("2", "Димитрий", "demitriy@t-bank.t", 2001)
    val st3 = Student("3", "Фемистоклюс", "fem-boy@t-bank.t", 2005)
    val st4 = Student("4", "Карен", "karen@t-bank.t", 2003)
    val st5 = Student("5", "Гремлин", "godzilla@t-bank.t", 2000)

    groupA.addUser(st1)
    groupA.addUser(st2)
    groupB.addUser(st3)
    groupB.addUser(st4)
    groupA.addUser(st5)

    //println(groupA.getInfo())
    //println(groupB.getInfo())

    groupA.removeUser(st1)
    groupB.addUser(st1)

    //println(groupA.getInfo())
    //println(groupB.getInfo())

    val lecturer = Lecturer("1", "Топиум Факультус Миганзов", "toples@t-bank.t",
        "Высшая математика", "Бакалавриат")

    //println(lecturer.getInfo())
    //println(st1.getInfo())

    lecturer.giveMarkToStudent(st1, 12, "Проект")

    //println(st1.getInfo())
    //println(st1.marks)
    //println(lecturer.givenMarks)

    val course = Course("1", "Матан", "Для всего университета", 0.0)

    //println(course.getInfo())

    val doc = CourseMaterial.Document("Секретный доумент", "...", 256)
    val vid = CourseMaterial.Video("Youtube-видео", 2.52, "(url)")

    val deadline = LocalDateTime.of(2025, 12, 31, 23, 59)
    val task = CourseMaterial.Assignment("Базовая задача", deadline, 12)

    //lecturer.assignCourse(course, doc)
    //lecturer.assignCourse(course, vid)
    //lecturer.assignCourse(course, task)

    //println(lecturer.getCourseMaterial(course))


}