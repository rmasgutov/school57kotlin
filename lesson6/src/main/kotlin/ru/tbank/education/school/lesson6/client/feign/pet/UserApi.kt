package ru.tbank.education.school.lesson6.client.feign.user

import feign.Headers
import feign.Param
import feign.RequestLine
import ru.tbank.education.school.lesson6.client.dto.ApiResponse
import ru.tbank.education.school.lesson6.client.dto.User
interface UserApi {
    @RequestLine("PUT /user/{username}")
    @Headers("Content-Type: application/json")
    fun updateUser(@Param("username") username: String, user: User): ApiResponse

    @RequestLine("POST /pet")
    @Headers("Content-Type: application/json")
    fun addUser(user: User): ApiResponse

    @RequestLine("GET /user/{username}")
    @Headers("Content-Type: application/json")
    fun getUser(@Param("username") username: String): User

    @RequestLine("DELETE /user/{username}")
    @Headers("Content-Type: application/json")
    fun deleteUser(@Param("username") username: String): ApiResponse
}//