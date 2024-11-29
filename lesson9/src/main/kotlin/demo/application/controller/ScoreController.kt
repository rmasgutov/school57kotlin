package demo.application.controller

import demo.application.dto.CreditApplication
import demo.application.service.ScoreService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class ScoreController(
    val scoreService: ScoreService,
) {

    @PostMapping("/score")
    fun simpleScore(@RequestBody creditApplication: CreditApplication) = scoreService.simpleScore(creditApplication)

}