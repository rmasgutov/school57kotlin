package demo.application

import demo.application.controller.ScoreController
import demo.application.dto.CreditApplication
import demo.application.dto.Loan
import demo.application.dto.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private lateinit var scoreController: ScoreController

    val testUser: User
        get() = User(
            age = (18..65).random(),
            name = "User",
            sex = User.Sex.entries.toTypedArray().random(),
            income = (10000..100000).random().toLong()
        )

    val testLoan
        get() = Loan(
            monthlyPayment = (100..1000).random().toLong(),
            amount = (1000..10000).random().toLong()
        )

    @Test
    fun `should not approve if user is under 18 years old`() {
        val creditApplication = CreditApplication(
            testLoan,
            testUser.copy(age = 17),
        )

        Assertions.assertFalse(
            scoreController.simpleScore(creditApplication),
            "Loan shouldn't be approved for users under 18 years old"
        )
    }
}