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

    fun updateUser(username: String, user: User)= feignClient.updateUser(username, user)

    fun getUser(username: String): User =
        feignClient.getUser(username)
}
fun main(){
    val id = Random.nextLong() * 1000
    val user = User(id = id, username="username", firstname="firstname", lastname="lastname",
        email="42@gmail.com", password="12345678", phone="79854784867", userStatus=1)
    val userClient = UserClient("https://petstore.swagger.io")

    userClient.addUser(user)
    println(userClient.updateUser(user.username, user.copy(username = "???")))
    println(userClient.getUser(user.username))
    println(userClient.deleteUser(user.username))
}