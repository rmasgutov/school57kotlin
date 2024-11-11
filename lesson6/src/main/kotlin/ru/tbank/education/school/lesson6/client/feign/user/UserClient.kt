package ru.tbank.education.school.lesson6.client.feign.user

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import kotlin.random.Random
import ru.tbank.education.school.lesson6.client.dto.User
import ru.tbank.education.school.lesson6.client.lessonObjectMapper

class UserClient(url: String) {
    private val feignClient =
        Feign.builder()
            .encoder(JacksonEncoder(lessonObjectMapper))
            .decoder(JacksonDecoder(lessonObjectMapper))
            .target(UserApi::class.java, url)

    fun addUser(user: User): ApiResponse = feignClient.addUser(user)

    fun deleteUser(username: String): ApiResponse = feignClient.deleteUser(username)

    fun updateUser(username: String, user: User): ApiResponse = feignClient.updateUser(username, user)

    fun getUser(username: String): User = feignClient.getUser(username)
}

fun main() {
    val newUser =
        User(
            id = Random.nextLong() * 1000,
            username = "iivanov",
            firstName = "Ivan",
            lastName = "Ivanov",
            email = "iivanov@example.com",
            password = "123456",
            phone = "89931112233",
            userStatus = 1
        )
    val userClient = UserClient("https://petstore.swagger.io")

    userClient.addUser(newUser)

    println(userClient.getUser(newUser.username))

    println(
        userClient.updateUser(newUser.username, newUser.copy(password = "5Wu2tFhmEtO9BFp4Bf8B"))
    )
    println(userClient.deleteUser(newUser.username))
}
