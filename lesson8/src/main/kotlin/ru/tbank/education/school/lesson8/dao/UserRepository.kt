package ru.tbank.education.school.lesson8.dao

import org.springframework.stereotype.Component
import ru.tbank.education.school.lesson8.dto.User

@Component
class UserRepository {
    private val users = mutableListOf<User>()

    fun getByUserName(userName: String): User? =
        users.find { it.userName == userName }

    fun addUser(user: User): User {
        users.add(user)
        return user
    }

    fun deleteUser(username: String) =
        users.removeIf { it.userName == username }

    fun updateUser(userName: String, user: User): User? {
        val existingUserIndex = users.indexOfFirst { it.userName == userName }
        if (existingUserIndex >= 0) {
            users[existingUserIndex] = users[existingUserIndex].copy(fullName = user.fullName, age = user.age)
            return user
        } else {
            return null
        }
    }
}