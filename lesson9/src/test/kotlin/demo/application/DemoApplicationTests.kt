package demo.application

import demo.application.controller.CreditApplication
import demo.application.controller.ScoreController
import demo.application.controller.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    lateinit var scoreController: ScoreController

    @Test
    fun someTest() {
        val request = CreditApplication(
            LocalDateTime.of(
                2001, 1, 1, 1, 1
            ), 2000, 50,
            User(
                45, "Bob", true, 1000, listOf()
            )
        )
        val result = scoreController.get_new_credit(request)
        Assertions.assertEquals(true, result)
    }

    @Test
    fun otherTest1() {
        val request = CreditApplication(
            LocalDateTime.of(
                2001, 1, 1, 1, 1
            ), 2000, 50,
            User(
                45, "Jack", true, 1000, listOf()
            )
        )
        val result = scoreController.get_new_credit(request)
        Assertions.assertEquals(false, result)
    }


    @Test
    fun otherTest2() {
        val request = CreditApplication(
            LocalDateTime.of(
                2001, 1, 1, 1, 1
            ), 2000, 50,
            User(
                17, "Bob", true, 1000, listOf()
            )
        )
        val result = scoreController.get_new_credit(request)
        Assertions.assertEquals(false, result)
    }


    @Test
    fun otherTest3() {
        val request = CreditApplication(
            LocalDateTime.of(
                2001, 1, 1, 1, 1
            ), 2000, 50,
            User(
                20, "Bob", true, 100, listOf()
            )
        )
        val result = scoreController.get_new_credit(request)
        Assertions.assertEquals(false, result)
    }


    @Test
    fun otherTest4() {
        val request = CreditApplication(
            LocalDateTime.of(
                2001, 1, 1, 1, 1
            ), 100, 20,
            User(
                20, "Bob", true, 100, listOf(
                    User.Loan(
                        LocalDateTime.of(
                            2001, 1, 1, 1, 1
                        ), false, 20
                    )
                )
            )
        )
        val result = scoreController.get_new_credit(request)
        Assertions.assertEquals(false, result)
    }


    @Test
    fun otherTest5() {
        val request = CreditApplication(
            LocalDateTime.of(
                2001, 1, 1, 1, 1
            ), 2000, 50,
            User(
                59, "Bob", true, 1000, listOf()
            )
        )
        val result = scoreController.get_new_credit(request)
        Assertions.assertEquals(false, result)
    }
}
