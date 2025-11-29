package demo.application.client

import demo.application.dto.User

interface UserGetter {

    fun getUserById(userId: String): User
}