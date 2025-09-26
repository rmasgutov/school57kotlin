package ru.tbank.education.school.lesson3.seminar.university.people

import ru.tbank.education.school.lesson3.seminar.bank.models.Group
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.Course
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.Mark

class Student(
    override val id: String,
    override val fullName: String,
    override val email: String,
    val enrollmentYear: Int
): User(id, fullName, email) {
    private val _marks = mutableListOf<Mark>()
    val marks: List<Mark> get() = _marks.toList()

    private val enrolledCourses = mutableSetOf<String>()

    protected fun calculateGPA(): Double {
        if (_marks.isEmpty()) return 0.0
        return _marks.map { it.value }.average()
    }

    fun addMark(mark: Mark) {
        _marks.add(mark)
    }

    override fun getRole(): String = "Студент"

    override fun getInfo(): String {
        val baseInfo = super.getInfo()
        return "$baseInfo, Год поступления: $enrollmentYear, Средний балл: ${calculateGPA()}, Кол-во оценок: ${_marks.size}"
    }

    fun enrollInCourse(course: Course) {
        enrolledCourses.add(course.id)
        println("Студент ${this.fullName} зачислен на курс: ${course.name}")
    }
}