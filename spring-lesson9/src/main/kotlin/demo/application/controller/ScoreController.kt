package demo.application.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
class ScoreController(
    val logicService: LogicService,
) {

    @GetMapping("score")
    fun simpleScore(@RequestParam creditApplication: CreditApplication) =
        logicService.simpleScore(creditApplication)


}

data class User(
    val age: Int,
    val name: String,
    val sex: Int,
    val income: Long,
    val loans: List<Loan>,
) {

    data class Loan(
        val creteAt: LocalDateTime,
        val isClose: Boolean,
        val monthlyPayment: Long,
    )
}

data class CreditApplication(
    val createdAt: LocalDateTime,
    val totalAmount: Long,
    val monthlyPayment: Long,
    val user: User,
    )