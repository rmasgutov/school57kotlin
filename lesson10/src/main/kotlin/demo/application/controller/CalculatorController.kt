package demo.application.controller

import demo.application.service.CalculatorService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(
        name = "Оценка кредитной нагрузки",
        description = "Эти методы вызываются напрямую с фронта, для расчета кредитной нагрузки"
)
@RestController
class CalculatorController(
        val calculatorService: CalculatorService,
) {

    @GetMapping("calculate/{userId}/")
    fun simpleScore(
            @PathVariable userId: String,
            @RequestParam monthlyPayment: Long,
    ): Long = calculatorService.run(userId, monthlyPayment)
}
