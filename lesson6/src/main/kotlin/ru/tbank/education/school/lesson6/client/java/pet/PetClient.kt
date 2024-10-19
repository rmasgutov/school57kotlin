package ru.tbank.education.school.lesson6.client.java.pet

import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import ru.tbank.education.school.lesson6.client.dto.Pet
import ru.tbank.education.school.lesson6.client.lessonObjectMapper
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.random.Random

class PetClient(private val url: String) {
    private val javaHttpClient = HttpClient.newBuilder().build()

    fun addPet(pet: Pet): Pet? {
        val body = lessonObjectMapper.writeValueAsString(pet)
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$url/v2/pet"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build()

        val response = javaHttpClient.send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() == 200 || response.statusCode() == 201) {
            lessonObjectMapper.readValue(response.body(), Pet::class.java)
        } else {
            null
        }
    }

    fun deletePet(petId: Long): ApiResponse? {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$url/v2/pet/$petId"))
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

    fun updatePet(pet: Pet): Pet? {
        val body = lessonObjectMapper.writeValueAsString(pet)
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$url/v2/pet"))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(body))
            .build()

        val response = javaHttpClient.send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() == 200 || response.statusCode() == 201) {
            lessonObjectMapper.readValue(response.body(), Pet::class.java)
        } else {
            null
        }
    }

    fun getPet(petId: Long): Pet? {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$url/v2/pet/$petId"))
            .header("Content-Type", "application/json")
            .GET()
            .build()

        val response = javaHttpClient.send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() == 200 || response.statusCode() == 201) {
            lessonObjectMapper.readValue(response.body(), Pet::class.java)
        } else {
            null
        }
    }
}

fun main() {
    val id = Random.nextLong() * 1000
    val newPet = Pet(id = id, name = "Дружок", status = "available")
    val petClient = PetClient("https://petstore.swagger.io")

    petClient.addPet(newPet)

    println(petClient.updatePet(newPet.copy(name = "Дружок 2")))
    println(petClient.deletePet(id))
}
