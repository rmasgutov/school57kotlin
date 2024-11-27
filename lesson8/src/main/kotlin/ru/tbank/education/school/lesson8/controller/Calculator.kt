package ru.tbank.education.school.lesson8.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Calculator {

    @RequestMapping("/calc", method = [RequestMethod.GET])
    fun greeting(@RequestParam("a") a: Int, @RequestParam("b") b: Int): String {
        var res = a + b
        return "result: $res"
    }
}