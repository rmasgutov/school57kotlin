package ru.tbank.education.school.lesson8.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class GreetingController {

    @RequestMapping("/hello", method = [RequestMethod.GET])
    fun greeting(@RequestParam("name") name: String): String {
        return "Hello, $name!"
    }
}