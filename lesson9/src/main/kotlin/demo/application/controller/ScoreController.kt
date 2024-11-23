package demo.application.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
class ScoreController(
    val logicService: LogicService,
) {

    @PostMapping("/score")
    fun simpleScore(@RequestBody creditApplication: CreditApplication) = logicService.simpleScore(creditApplication)

}

data class User(
    val age: Int,
    val name: String,
    val sex: Sex,
    val income: Long,
    val loans: List<Loan>,
) {

    data class Loan(
        val createdAt: LocalDateTime,
        val isClosed: Boolean,
        val monthlyPayment: Long,
    )

    enum class Sex {
        MALE, FEMALE
    }

}

data class CreditApplication(
    val createdAt: LocalDateTime,
    val totalAmount: Long,
    val monthlyPayment: Long,
    val user: User,
)