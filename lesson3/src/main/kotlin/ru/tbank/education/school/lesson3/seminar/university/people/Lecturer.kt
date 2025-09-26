package ru.tbank.education.school.lesson3.seminar.university.people

import ru.tbank.education.school.lesson3.seminar.university.dataclasses.Course
import ru.tbank.education.school.lesson3.seminar.university.dataclasses.Mark
import ru.tbank.education.school.lesson3.seminar.university.sealedclasses.CourseMaterial
import java.util.*

class Lecturer(
    override val id: String,
    override val fullName: String,
    override val email: String,
    val department: String,
    val academicDegree: String,
): User(id, fullName, email) {
    private val _givenMarks = mutableListOf<Mark>()
    val givenMarks: List<Mark> get() = _givenMarks.toList()

    private val taughtCourses = mutableListOf<Course>()

    private val courseMaterials = mutableMapOf<String, MutableList<CourseMaterial>>()

    override fun getInfo(): String {
        val baseInfo = super.getInfo()
        return "$baseInfo, Кафедра: $department, Степень: $academicDegree"
    }

    fun assignCourse(course: Course, material: CourseMaterial) {
        courseMaterials.getOrPut(course.id) { mutableListOf() }.add(material)
        println("Добавлен материал для курса ${course.name}: ${material.getDescription()}")
    }

    fun getCourseMaterial(course: Course): List<CourseMaterial> {
        return courseMaterials[course.id] ?: emptyList()
    }

    fun addGivenMark(mark: Mark) {
        _givenMarks.add(mark)
    }

    fun giveMarkToStudent(student: Student, value: Int, description: String): Mark {
        val mark = Mark(
            id = UUID.randomUUID().toString(),
            from = this,
            to = student,
            value = value,
            description
        )

        mark.sendAndSave()
        return mark
    }

    fun CourseMaterial.getDescription(): String = when(this) {
        is CourseMaterial.Document -> "Документ $title ($size KB)"
        is CourseMaterial.Video -> "Видео $title ($duration мин)"
        is CourseMaterial.Assignment -> "Задание $title (до $deadline)"
        CourseMaterial.Empty -> "Пустой материал"
    }

    override fun getRole(): String = "Преподаватель"
}