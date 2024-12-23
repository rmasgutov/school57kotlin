package demo.application

import demo.application.controller.CreditApplication
import demo.application.controller.LogicService
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

    fun someTest() {
        scoreController = ScoreController(LogicService())
        val request = CreditApplication(
            createdAt = LocalDateTime.now(),
            totalAmount = 1000000,
            monthlyPayment = 20000,
            user = User(age = 32, name = "Alex", sex = 1, income = 250000,
                loans = listOf(User.Loan(LocalDateTime.now(), true, 10000))))
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(true, result)
    }


    fun otherTest1() {
        scoreController = ScoreController(LogicService())
        val request = CreditApplication(
            createdAt = LocalDateTime.now(),
            totalAmount = 1000000,
            monthlyPayment = 20000,
            user = User(age = 16, name = "Alex", sex = 1, income = 200000,
                loans = listOf(User.Loan(LocalDateTime.now(), true, 10000))))
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(false, result)
    }
    fun otherTest2() {
        scoreController = ScoreController(LogicService())
        val request = CreditApplication(
            createdAt = LocalDateTime.now(),
            totalAmount = 1000000,
            monthlyPayment = 20000,
            user = User(age = 23, name = "Alex", sex = 1, income = 35000,
                loans = listOf(User.Loan(LocalDateTime.now(), true, 10000))))
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(false, result)
    }

}
