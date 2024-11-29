package demo.application.controller

import demo.application.dto.CreditApplication
import demo.application.service.LogicService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class ScoreController(
    val logicService: LogicService,
) {
    @PostMapping("/score")
    fun simpleScore(@RequestBody creditApplication: CreditApplication) = logicService.simpleScore(creditApplication)
}