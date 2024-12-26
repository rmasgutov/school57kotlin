package ru.tbank.education.school.lesson6.client.java.user
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import ru.tbank.education.school.lesson6.client.dto.User
import ru.tbank.education.school.lesson6.client.lessonObjectMapper
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.math.abs
import kotlin.random.Random
class UserClient(
    private val url: String
) {

    private val httpClient = HttpClient.newHttpClient()

    fun createUser(user: User): ApiResponse {
        val body = lessonObjectMapper.writeValueAsString(user)
        return makeRequest("POST", "/user", body, ApiResponse::class.java)
    }

    fun getUser(username: String): User {
        return makeRequest("GET", "/user/$username", User::class.java)
    }

    fun updateUser(user: User): ApiResponse {
        val body = lessonObjectMapper.writeValueAsString(user)
        return makeRequest("PUT", "/user/${user.username}", body, ApiResponse::class.java)
    }

    fun deleteUser(username: String): ApiResponse {
        return makeRequest("DELETE", "/user/$username", ApiResponse::class.java)
    }

    private fun <T> makeRequest(method: String, path: String, dataType: Class<T>): T {
        return makeRequest(method, path, null, dataType)
    }

    private fun <T> makeRequest(method: String, path: String, data: String?, dataType: Class<T>): T {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$url$path"))
            .header("Content-Type", "application/json")
            .method(method, HttpRequest.BodyPublishers.ofString(data ?: ""))
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() == 200 || response.statusCode() == 201) {
            lessonObjectMapper.readValue(response.body(), dataType)
        } else {
            throw RuntimeException("Получен статус код ${response.statusCode()}")
        }
    }

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
}//