package ru.tbank.education.school.lesson6.client.feign.pet

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.Pet
import ru.tbank.education.school.lesson6.client.dto.User
import ru.tbank.education.school.lesson6.client.feign.user.UserApi
import ru.tbank.education.school.lesson6.client.java.user.UserClient
import ru.tbank.education.school.lesson6.client.lessonObjectMapper
import kotlin.random.Random

class UserClient(url: String) {
    private val feignClient =
        Feign
            .builder()
            .encoder(JacksonEncoder(lessonObjectMapper))
            .decoder(JacksonDecoder(lessonObjectMapper))
            .target(UserApi::class.java, url)

    fun addUser(user: User) = feignClient.addUser(user)

    fun deleteUser(username: String) = feignClient.deleteUser(username)

    fun updateUser(username: String, user: User) =
        feignClient.updateUser(username, user)

    fun getUser(username: String) : User =
        feignClient.getUser(username)
}

fun main() {
    val id = Random.nextLong() * 1000
    val newUser =
        User(
            id = Random.nextLong() * 1000,
            username = "authentic",
            firstName = "Maria",
            lastName = "Nepomyaschaya",
            email = "mmm@gmail.com",
            password = "1337",
            phone = "89116778808",
            userStatus = 1
        )
    val UserClient = UserClient("https://Userstore.swagger.io")

    UserClient.addUser(newUser)
    println(UserClient.updateUser(newUser.username, newUser.copy(username = "???")))
    println(UserClient.getUser(newUser.username))
    println(UserClient.deleteUser(newUser.username))
}
