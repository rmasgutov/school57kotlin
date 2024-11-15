package ru.tbank.education.school.lesson6.client.feign.user

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.User
import ru.tbank.education.school.lesson6.client.lessonObjectMapper
import kotlin.random.Random

class UserClient(url: String) {


    private val feignClient = Feign
        .builder()
        .encoder(JacksonEncoder(lessonObjectMapper))
        .decoder(JacksonDecoder(lessonObjectMapper))
        .target(UserApi::class.java, url)

    fun createUser(user: User) = feignClient.createUser(user)

    fun deleteUser(username: String) = feignClient.deleteUser(username)

    fun updateUser(user: User) = feignClient.updateUser(user.username, user)

    fun getUser(username: String) = feignClient.getUser(username)

}


fun main() {
    val id = Random.nextLong()
    val user = User(
        id = id,
        username = "idk",
        firstName = "sus",
        lastName = "susus",
        email = "idk@mail.ru",
        password = "123",
        phone = "112",
        userStatus = 0
    )
    val userClient = UserClient("https://petstore.swagger.io/v2")

    println(userClient.createUser(user))
    println(userClient.getUser(user.username))
    println(userClient.updateUser(user.copy(firstName = "bimbam")))
    println(userClient.getUser(user.username))
    println(userClient.deleteUser(user.username))
    println(userClient.getUser(user.username))
}