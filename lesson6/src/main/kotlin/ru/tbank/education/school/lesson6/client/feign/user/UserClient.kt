package ru.tbank.education.school.lesson6.client.feign.user

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.User
import ru.tbank.education.school.lesson6.client.lessonObjectMapper
import kotlin.random.Random

class UserClient(url: String) {
    private val feignClient = Feign.builder()
        .encoder(JacksonEncoder(lessonObjectMapper))
        .decoder(JacksonDecoder(lessonObjectMapper))
        .target(UserApi::class.java, url)

    fun createUser(user: User) = feignClient.createUser(user)

    fun getUser(username: String): User = feignClient.getUser(username)

    fun updateUser(username: String, user: User) = feignClient.updateUser(username, user)

    fun deleteUser(username: String) = feignClient.deleteUser(username)
}

fun main() {
    val userClient = UserClient("https://petstore.swagger.io")
    val newUser = User(
        id = Random.nextLong() * 1000,
        username = "corg",
        firstName = "test",
        lastName = "netest",
        email = "testn@pochta.com",
        password = "0000",
        phone = "88005553535",
        userStatus = 1
    )

    userClient.createUser(newUser)
    println("Создан пользователь: ${userClient.getUser(newUser.username)}")

    val updatedUser = newUser.copy(firstName = "Johnny")
    userClient.updateUser(newUser.username, updatedUser)
    println("Обновленный пользователь: ${userClient.getUser(updatedUser.username)}")

    userClient.deleteUser(newUser.username)
    println("Пользователь удален")
}