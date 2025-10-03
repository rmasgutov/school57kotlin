package ru.tbank.education.school.lesson3.seminar.university.people

import ru.tbank.education.school.lesson3.seminar.university.interfaces.Authenticatable
import ru.tbank.education.school.lesson3.seminar.university.interfaces.Informable
import ru.tbank.education.school.lesson3.seminar.university.models.Group


abstract class User(
    open val id: String,
    open val fullName: String,
    open val email: String,
) : Authenticatable, Informable {
    open var group: Group = Group("1", "None")
    protected var password: String = ""

    override fun authenticate(inputPassword: String): Boolean {
        return password == inputPassword
    }

    override fun changePassword(newPassword: String) {
        if (newPassword == password) {print("Пароль уже был использован ранее")}
        else {
            password = newPassword
        }
    }

    override fun getInfo(): String {
        return "Айди: $id, ФИО: $fullName, Группа: ${group.fullName}"
    }

    constructor(id: String, fullName: String, group: Group, email: String) : this(id, fullName, email) {
        this.group = group
    }

    abstract override fun getRole(): String
}