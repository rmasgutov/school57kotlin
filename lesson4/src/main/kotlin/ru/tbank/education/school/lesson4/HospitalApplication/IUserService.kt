package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

interface IUserService {
    fun registerUser(user: User): Boolean
    fun loginUser(email: String, password: String): User?
}