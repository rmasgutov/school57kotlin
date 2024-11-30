package demo.application.controller

import demo.application.dto.CreditApplication
import demo.application.service.ScoreService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@Tag(name = "Скоринг заявки", description = "Эти методы предназначены для вызова из процесса оформления заявки")
@RestController
class ScoreController(private val scoreService: ScoreService) {

    @PostMapping("/score")
    fun simpleScore(@RequestBody creditApplication: CreditApplication) = scoreService.simpleScore(creditApplication)

}

