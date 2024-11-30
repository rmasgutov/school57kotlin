package demo.application.controller

import demo.application.dto.CreditApplication
import demo.application.services.ServiceScore
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@Tag(name = "Скоринг заявки", description = "Эти методы предназначены для вызова из процесса оформления заявки")
@RestController
class ScoreController (
    val Servicescore : ServiceScore,
){

    @GetMapping("score")
    fun simpleScore(@RequestBody creditApplication: CreditApplication) = Servicescore.getScore(creditApplication)
}

