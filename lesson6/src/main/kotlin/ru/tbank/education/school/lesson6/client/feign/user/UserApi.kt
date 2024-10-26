package ru.tbank.education.school.lesson6.client.feign.user

import feign.Headers
import feign.Param
import feign.RequestLine
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import ru.tbank.education.school.lesson6.client.dto.Pet

interface UserApi {
    @RequestLine("PUT /v2/pet")
    @Headers("Content-Type: application/json")
    fun updateUser(user: User): User

    @RequestLine("POST /v2/pet")
    @Headers("Content-Type: application/json")
    fun addUser(user: User): User
    @RequestLine("GET /v2/pet/{pathId}")
    @Headers("Content-Type: application/json")
    fun getUser(@Param("pathId") userId: Long): User

    @RequestLine("DELETE /v2/pet/{pathId}")
    @Headers("Content-Type: application/json")
    fun deleteUser(@Param("pathId") userId: Long): ApiResponse
}
