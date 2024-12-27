package ru.tbank.education.school.lesson6.client.feign.user


import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
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

    fun updateUser(username: String, user: User): ApiResponse =
        feignClient.updateUser(username, user)

    fun getUser(username: String): User =
        feignClient.getUser(username)
}

fun main() {
    val id = Random.nextLong() * 1000
    val newUser = User(id = id, username = "ivanxdxd", firstName = "ivan", lastName = "ivanov", email = "ivanxdxd@gmail.com",
        password = "123", phone = "88005553535", userStatus = 777)
    val userClient = UserClient("https://petstore.swagger.io")

    userClient.addUser(newUser)

    println(userClient.getUser("ivanxdxd"))
//
    println(userClient.updateUser(username = "anton", newUser.copy(username = "anton")))
    println(userClient.deleteUser("ivanxdxd"))
}
