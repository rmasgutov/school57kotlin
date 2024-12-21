package ru.tbank.education.school.lesson8.service

import org.springframework.stereotype.Component
import ru.tbank.education.school.lesson8.dao.UserRepository
import ru.tbank.education.school.lesson8.dto.User

@Component
class UserService(
    private val userRepository: UserRepository
) {

    fun getUser(userName: String) =
        userRepository.getByUserName(userName)

    fun addUser(user: User) =
        if (getUser(user.userName) != null)
            throw IllegalArgumentException("ALREADY EXISTS")
        else
            userRepository.addUser(user)

    fun deleteUser(userName: String) =
        if (userRepository.deleteUser(userName))
            "Successful"
        else
            "Failed"

    fun updateUser(userName: String, user: User) =
        userRepository.updateUser(userName, user) ?: throw IllegalArgumentException("User not found")
}