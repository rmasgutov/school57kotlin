package ru.tbank.education.school.lesson6.client.java.user

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.random.Random
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import ru.tbank.education.school.lesson6.client.dto.User
import ru.tbank.education.school.lesson6.client.lessonObjectMapper

class UserClient(private val url: String) {
    private val javaHttpClient = HttpClient.newBuilder().build()

    fun addUser(user: User): ApiResponse? {
        val body = lessonObjectMapper.writeValueAsString(user)
        val request =
            HttpRequest.newBuilder()
                .uri(URI.create("$url/v2/user"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build()

        val response = javaHttpClient.send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() == 200 || response.statusCode() == 201) {
            lessonObjectMapper.readValue(response.body(), ApiResponse::class.java)
        } else {
            null
        }
    }

    fun deleteUser(username: String): ApiResponse? {
        val request =
            HttpRequest.newBuilder()
                .uri(URI.create("$url/v2/user/$username"))
                .header("Content-Type", "application/json")
                .DELETE()
                .build()

        val response = javaHttpClient.send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() == 200 || response.statusCode() == 201) {
            lessonObjectMapper.readValue(response.body(), ApiResponse::class.java)
        } else {
            null
        }
    }

    fun updateUser(username: String, user: User): ApiResponse? {
        val body = lessonObjectMapper.writeValueAsString(user)
        val request =
            HttpRequest.newBuilder()
                .uri(URI.create("$url/v2/user/$username"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build()

        val response = javaHttpClient.send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() == 200 || response.statusCode() == 201) {
            lessonObjectMapper.readValue(response.body(), ApiResponse::class.java)
        } else {
            null
        }
    }

    fun getUser(username: String): User? {
        val request =
            HttpRequest.newBuilder()
                .uri(URI.create("$url/v2/user/$username"))
                .header("Content-Type", "application/json")
                .GET()
                .build()

        val response = javaHttpClient.send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() == 200 || response.statusCode() == 201) {
            lessonObjectMapper.readValue(response.body(), User::class.java)
        } else {
            null
        }
    }
}

fun main() {
    val newUser =
        User(
            id = Random.nextLong() * 1000,
            username = "iivanov",
            firstName = "Ivan",
            lastName = "Ivanov",
            email = "iivanov@example.com",
            password = "123456",
            phone = "89931112233",
            userStatus = 1
        )
    val userClient = UserClient("https://petstore.swagger.io")

    userClient.addUser(newUser)

    println(userClient.getUser(newUser.username))

    println(
        userClient.updateUser(newUser.username, newUser.copy(password = "5Wu2tFhmEtO9BFp4Bf8B"))
    )
    println(userClient.deleteUser(newUser.username))
}
