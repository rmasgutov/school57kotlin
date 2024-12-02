package demo.application.controller

import demo.application.service.CalculationService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Оценка кредитной нагрузки", description = "Эти методы вызываются напрямую с фронта, для расчета кредитной нагрузки")
@RestController
class CalculatorController(
    val calculationService: CalculationService
) {


    @GetMapping("calculate/userId={userId}/")
    fun simpleScore(
        @PathVariable userId: String,
        @RequestParam monthlyPayment: Long,
    ): Long {
        return calculationService.calculate(userId, monthlyPayment)
    }
}