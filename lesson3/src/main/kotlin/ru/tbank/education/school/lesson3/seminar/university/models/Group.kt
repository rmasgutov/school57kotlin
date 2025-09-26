package ru.tbank.education.school.lesson3.seminar.bank.models

import ru.tbank.education.school.lesson3.seminar.university.people.User

data class Group(val id: String, val fullName: String) {
    private val _users = mutableListOf<User>()
    val users: List<User> get() = _users.toList()

    fun addUser(user: User): Boolean {
        if (user.group == this) return false

        user.group.removeUser(user)

        user.group = this
        _users.add(user)
        return true
    }


    fun removeUser(user: User): Boolean {
        if (user.group != this) return false
        user.group = Group("1", "Allien")
        return _users.remove(user)
    }

    fun getInfo(): String {
        val usersList = users.joinToString("\n") { " - ${it.fullName} (${it.email})" }
        return """
            |Группа: $fullName
            |Количество участников: ${users.size}
            |Участники группы:
            ${usersList}
        """.trimIndent()
    }
}