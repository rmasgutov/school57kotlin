package ru.tbank.education.school.lesson6.client.feign.pet

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import ru.tbank.education.school.lesson6.client.dto.Pet
import ru.tbank.education.school.lesson6.client.lessonObjectMapper
import kotlin.math.abs
import kotlin.random.Random

class PetClient(url: String) {
    private val feignClient =
        Feign
            .builder()
            .encoder(JacksonEncoder(lessonObjectMapper))
            .decoder(JacksonDecoder(lessonObjectMapper))
            .target(PetApi::class.java, url)

    fun addPet(pet: Pet): Pet = feignClient.addPet(pet)

    fun deletePet(petId: Long): ApiResponse = feignClient.deletePet(petId)

    fun updatePet(pet: Pet): Pet = feignClient.updatePet(pet)

    fun getPet(petId: Long): Pet = feignClient.getPet(petId)
}

fun main() {
    val id = abs(Random.nextLong() * 1000)
    val newPet = Pet(id = id, name = "Дружок", status = "available")
    val petClient = PetClient("https://petstore.swagger.io")

    petClient.addPet(newPet)

    println(petClient.getPet(id))

    println(petClient.updatePet(newPet.copy(name = "Дружок 2")))
    println(petClient.deletePet(id))
}
