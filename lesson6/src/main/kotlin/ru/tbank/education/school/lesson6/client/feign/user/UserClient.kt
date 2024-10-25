package ru.tbank.education.school.lesson6.client.feign.user

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.User
import ru.tbank.education.school.lesson6.client.java.user.UserClient
import ru.tbank.education.school.lesson6.client.lessonObjectMapper
import kotlin.math.abs
import kotlin.random.Random

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
class UserClient(url: String) {

    private val feignClient = Feign
        .builder()
        .encoder(JacksonEncoder(lessonObjectMapper))
        .decoder(JacksonDecoder(lessonObjectMapper))
        .target(UserApi::class.java, url)

    fun createUser(user: User) = feignClient.createUser(user)

    fun deletePet(username: String) = feignClient.deleteUser(username)

    fun updatePet(user: User) = feignClient.updateUser(user.username, user)

    fun getUser(username: String) = feignClient.getUser(username)

}


fun main() {
    val id = Random.nextLong()
    val user = User(
        id = id,
        username = abs(Random.nextInt()).toString(),
        firstName = "Хм",
        lastName = "Хмхм",
        email = "a@b.c",
        password = "123",
        phone = "123",
        userStatus = 1
    )
    val userClient = UserClient("https://petstore.swagger.io/v2")

    println(userClient.createUser(user))
    println(userClient.getUser(user.username))
    println(userClient.updateUser(user.copy(firstName = "Хм 2")))
    println(userClient.getUser(user.username))
    println(userClient.deleteUser(user.username))
    println(userClient.getUser(user.username))
}
