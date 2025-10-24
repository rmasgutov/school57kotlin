package ru.tbank.education.school.lesson8.controller

import org.springframework.web.bind.annotation.*
import ru.tbank.education.school.lesson8.dto.User
import ru.tbank.education.school.lesson8.service.UserService

@RestController
class UserController(
    private val userService: UserService
) {
    @GetMapping("/users/{username}")
    fun getUserWithUserName(@PathVariable("username") username: String) =
        userService.getUser(username)

    @PostMapping("/users")
    fun createUser(@RequestBody user: User) =
        userService.addUser(user)

    @DeleteMapping("/users/{username}")
    fun deleteUser(@PathVariable("username") username: String) =
        userService.deleteUser(username)

    @PutMapping("/users/{username}")
    fun deleteUser(@PathVariable("username") username: String, @RequestBody user: User) =
        userService.updateUser(username, user)
}