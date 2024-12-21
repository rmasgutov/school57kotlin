package demo.application

import demo.application.controller.CreditApplication
import demo.application.controller.Loan
import demo.application.controller.ScoreController
import demo.application.controller.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    lateinit var scoreController: ScoreController

    @Test
    fun someTest() {
        val user = User(45,"ДАННЫЕ УДАЛЕНЫ", 1, 800, 52, listOf())
        val request = CreditApplication(LocalDateTime.now(), 0, user.loans.sumOf{it.monthlyPayment}, user)
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(true, result)
    }
    @Test
    fun otherTest() {
        val user = User(32,"ДАННЫЕ УДАЛЕНЫ", 1, 800, 52, listOf(Loan(LocalDateTime.now(), LocalDateTime.now(), false, 800)))
        val request = CreditApplication(LocalDateTime.now(), 800, user.loans.sumOf{it.monthlyPayment}, user)
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(false, result)
    }
}