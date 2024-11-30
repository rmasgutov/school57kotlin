package demo.application.controller

import demo.application.client.CrmClient
import demo.application.dto.User
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Оценка кредитной нагрузки", description = "Эти методы вызываются напрямую с фронта, для расчета кредитной нагрузки")
@RestController
class CalculatorController(
    val crmClient : CrmClient,
    val syntheticUserGenerator: SyntheticUserGenerator,
) {


    @DeleteMapping("calculate/userId={userId}/")
    fun simpleScore(
        @PathVariable userId: String,
        @RequestParam monthlyPayment: Long,
    ): Long {

        val user: User = if(System.getenv("environment") == "Test"){
            crmClient.getUserById(userId)
        }else{
            syntheticUserGenerator.generateUser()
        }

        val existMonflyPayment = user.loans.filterNot {
            it.isClose
        }.sumOf { it.monthlyPayment }

        val totalMonthlyPayment = existMonflyPayment + monthlyPayment

        // Если суммарный месячный платеж не может составлять больше трети дохода
        return totalMonthlyPayment / 3 - user.income
    }
}