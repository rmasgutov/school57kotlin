package ru.tbank.education.school.lesson8.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class CalculatorController {
    @RequestMapping("/sum", method = [RequestMethod.GET])
    fun sum(@RequestParam("a") a: Int, @RequestParam("b") b: Int): Int {
        return a + b
    }
}