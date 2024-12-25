package ru.tbank.education.school.lesson6.client.feign.user

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.User
import ru.tbank.education.school.lesson6.client.java.user.UserClient
import ru.tbank.education.school.lesson6.client.lessonObjectMapper
import kotlin.random.Random

class UserClient(url: String) {
    private val feignClient =
        Feign.builder().encoder(JacksonEncoder(lessonObjectMapper)).decoder(JacksonDecoder(lessonObjectMapper)).target(UserApi::class.java, url)

    fun addUser(user: User) = feignClient.addUser(user)

    fun deleteUser(username: String) = feignClient.deleteUser(username)

    fun updateUser(username: String, user: User)= feignClient.updateUser(username, user)

    fun getUser(username: String): User =
        feignClient.getUser(username)
}
fun main(){
    val id = Random.nextInt() * 1000
    val New_User = User(id = id, username="mikhail_nikoruk", firstname="miklhail", lastname="nikoruk",
        email="nmv0808@mail.ru", password="12345678", phone="+79091207483", userStatus=1)
    val userClient = UserClient("https://petstore.swagger.io")

    userClient.addUser(New_User)
    println(userClient.updateUser(New_User.username, New_User.copy(username = "???")))
    println(userClient.getUser(New_User.username))
    println(userClient.deleteUser(New_User.username))
}
//