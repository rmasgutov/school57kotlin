package demo.application

import demo.application.controller.CreditApplication
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
    fun Test1() {
        val request = CreditApplication(
            LocalDateTime.of(
                2024, 12, 14, 23, 58
            ), 5000, 70,
            User(
                50, "Владимир", "male", 2000, listOf()
            )
        )
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(true, result)
    }

    @Test
    fun Test2() {
        val request = CreditApplication(
            LocalDateTime.of(
                2024, 12, 14, 23, 58
            ), 5000, 70,
            User(
                18, "Мария", "female", 100, listOf()
            )
        )
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(false, result)
    }

    @Test
    fun Test3() {
        val request = CreditApplication(
            LocalDateTime.of(
                2024, 12, 14, 23, 58
            ), 5000, 70,
            User(
                17, "Виктория", "female", 5000, listOf()
            )
        )
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(false, result)
    }

    @Test
    fun Test4() {
        val request = CreditApplication(
            LocalDateTime.of(
                2024, 12, 14, 23, 58
            ), 5000, 70,
            User(
                64, "Михаил", "male", 5000, listOf(User.Loan(LocalDateTime.of(2022, 12, 14, 12, 0), false, 1000))
            )
        )
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(false, result)
    }
}
