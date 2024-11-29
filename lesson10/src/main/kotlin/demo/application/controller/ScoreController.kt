package demo.application.controller

import demo.application.dto.CreditApplication
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
class ScoreController {

    @GetMapping("score")
    fun simpleScore(@RequestBody creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        if (creditApplication.user.age <= 18) {
            return false
        }

        val existMonflyPayment = creditApplication.user.loans.filterNot {
            it.isClose
        }.sumOf { it.monthlyPayment }

        val totalMonthlyPayment = existMonflyPayment + creditApplication.monthlyPayment

        // Если суммарный месячный платеж составляет больше трети дохода то нельзя выдавать новый кредит
        return totalMonthlyPayment / 3 < creditApplication.user.income
    }



}

