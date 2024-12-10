package ru.tbank.education.school.lesson6.client.feign.user

import feign.Headers
import feign.Param
import feign.RequestLine
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import ru.tbank.education.school.lesson6.client.dto.User
interface UserApi {
    @RequestLine("PUT /v2/user/{username}")
    @Headers("Content-Type: application/json")
    @Param("username")
    fun updateUser(user: String, user1: User): ApiResponse

    @RequestLine("POST /v2/user")
    @Headers("Content-Type: application/json")
    fun addUser(user: User): ApiResponse

    @RequestLine("GET /v2/user/{username}")
    @Headers("Content-Type: application/json")
    fun getUser(@Param("username") username: String): User

    @RequestLine("DELETE /v2/user/{username}")
    @Headers("Content-Type: application/json")
    fun deleteUser(@Param("username") username: String): ApiResponse
}