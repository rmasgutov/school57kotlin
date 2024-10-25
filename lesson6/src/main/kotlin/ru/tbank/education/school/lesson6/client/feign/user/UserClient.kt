package ru.tbank.education.school.lesson6.client.feign.user

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.User
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import ru.tbank.education.school.lesson6.client.lessonObjectMapper
import kotlin.math.abs
import kotlin.random.Random

class UserClient(url: String) {
    private val feignClient =
        Feign
            .builder()
            .encoder(JacksonEncoder(lessonObjectMapper))
            .decoder(JacksonDecoder(lessonObjectMapper))
            .target(UserApi::class.java, url)

    fun addUser(user: User): ApiResponse = feignClient.addUser(user)

    fun deleteUser(username: String): ApiResponse = feignClient.deleteUser(username)

    fun updateUser(username: String, user: User): ApiResponse = feignClient.updateUser(username, user)

    fun getUser(username: String): User = feignClient.getUser(username)
}

fun main() {
    val id = abs(Random.nextLong() * 1000)
    val newUser =
        User(
            id = id,
            username = "PS",
            firstName = "Peter",
            lastName = "Sidorov",
            email = "petsid@gmail.com",
            password = "000000000",
            phone = "79162055437",
            userStatus = 1
        )
    val userClient = UserClient("https://petstore.swagger.io")

    userClient.addUser(newUser)

    println(userClient.getUser(newUser.username))

    println(userClient.updateUser(newUser.username, newUser.copy(email = "PETERSIDOROV@mail.ru")))

    println(userClient.deleteUser(newUser.username))
}