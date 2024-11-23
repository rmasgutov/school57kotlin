package demo.application.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import demo.application.service.LogicService
import demo.application.dto.CreditApplication


@RestController
class ScoreController(
    private val logicService: LogicService,
) {

    @GetMapping("score")
    fun simpleScore(@RequestParam creditApplication: CreditApplication) =
        logicService.simpleScore(creditApplication)


}

