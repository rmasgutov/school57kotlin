package ru.tbank.education.school.lesson6.client.feign.user

import feign.Headers
import feign.Param
import feign.RequestLine
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import ru.tbank.education.school.lesson6.client.dto.Pet

interface UserApi {
    @RequestLine("PUT /v2/pet")
    @Headers("Content-Type: application/json")
    fun updatePet(pet: Pet): Pet

    @RequestLine("POST /v2/pet")
    @Headers("Content-Type: application/json")
    fun addPet(pet: Pet): Pet

    @RequestLine("GET /v2/pet/{pathId}")
    @Headers("Content-Type: application/json")
    fun getPet(@Param("pathId") petId: Long): Pet

    @RequestLine("DELETE /v2/pet/{pathId}")
    @Headers("Content-Type: application/json")
    fun deletePet(@Param("pathId") petId: Long): ApiResponse
}
