package ru.tbank.education.school.lesson3.seminar.university

import ru.tbank.education.school.lesson3.seminar.university.dataclasses.Course
import ru.tbank.education.school.lesson3.seminar.university.sealedclasses.OperationResult

class CourseService {
    private val courses = mutableMapOf<String, Course>()

    fun createCourse(id: String, fullName: String, description: String, credits: Double): OperationResult<Course> {
        return if (courses.containsKey(id)) {
            OperationResult.Error("Курс с таким ID уже существует")
        } else {
            val course = Course(id, fullName, description, credits)
            courses[id] = course
            OperationResult.Success(course)
        }
    }

    fun getCourseById(id: String): Course? = courses[id]
    fun getAllCourses(): List<Course> = courses.values.toList()
}