package ru.tbank.education.school.lesson3.seminar.university.people

import ru.tbank.education.school.lesson3.seminar.university.models.Group


abstract class User(
    open val id: String,
    open val fullName: String,
    open val email: String,
) {
    open var group: Group = Group("1", "None")
    protected var password: String = ""

    open fun authenticate(inputPassword: String): Boolean {
        return password == inputPassword
    }

    open fun changePassword(newPassword: String) {
        if (newPassword == password) {print("Пароль уже был использован ранее")}
        else {
            password = newPassword
        }
    }

    open fun getInfo(): String {
        return "Айди: $id, ФИО: $fullName, Группа: ${group.fullName}"
    }

    constructor(id: String, fullName: String, group: Group, email: String) : this(id, fullName, email) {
        this.group = group
    }

    abstract fun getRole(): String
}