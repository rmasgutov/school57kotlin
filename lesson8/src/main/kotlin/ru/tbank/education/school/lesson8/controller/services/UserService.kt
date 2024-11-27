package ru.tbank.education.school.lesson8.controller.services

import ru.tbank.education.school.lesson8.controller.dto.User
import kotlin.random.Random

class UserService {

    fun getUser(username: String) =
        User(id = 0, userName = username, fullName = "FULLNAME", age = Random.nextInt(0, 100))
}
