package ru.tbank.education.school.lesson6.client.feign.user

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.User
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

    fun getUser(username: String) =
        feignClient.getUser(username)
}

fun main() {
    val user = User(
        id = Random.nextLong() * 1000,
        username = "евгений",
        firstName = "евгений",
        lastName = "евгений",
        email = "евгений",
        password = "евгений",
        phone = "88005553535",
        userStatus = 1
    )

    val userClient = UserClient(url = "https://petstore.swagger.io")
    userClient.addUser(user)
    println(userClient.getUser(user.username))
    println(userClient.updateUser(user.username, user.copy(password = "zahar 2")))
    println(userClient.getUser(user.username))
    println(userClient.deleteUser(user.username))

}//