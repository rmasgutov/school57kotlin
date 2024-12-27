package demo.application

import demo.application.controller.CreditApplication
import demo.application.controller.ScoreController
import demo.application.controller.User
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import kotlin.random.Random

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    lateinit var scoreController: ScoreController

    fun createLoan(isClose: Boolean): User.Loan{
        return User.Loan(
            monthlyPayment = (100..1000).random().toLong(),
            remainingAmount = (1000..10000).random().toLong(),
            creteAt = LocalDateTime.now(),
            isClose = isClose,
        )
    }
    fun createUser(n: Int = 0, name:String = "test",  age: Int = (18..65).random(), income: Long = (10000..100000).random().toLong()): User{
        return User(
            age = age,
            name = name,
            sex = Random.nextInt(1, 2),
            income = income,
            loans = List(n) { createLoan(isClose = false) }
        )
    }

    fun `do not issue a loan if age lower than 18`() {
        val request = CreditApplication(
            createdAt = LocalDateTime.now(),
            totalAmount = (2000..100000).random().toLong(),
            monthlyPayment = (100..1000).random().toLong(),
            user = createUser(age = 15)
        )
        val result = scoreController.simpleScore(request)
        Assertions.assertFalse(result)
    }


    fun `do not issue a loan if income is too low`() {

        val request = CreditApplication(
            createdAt = LocalDateTime.now(),
            totalAmount = (2000..100000).random().toLong(),
            monthlyPayment = 0,
            user = createUser(income = 0, n = 20)
        )
        val result = scoreController.simpleScore(request)
        Assertions.assertFalse(result)
    }
    fun ` issue a loan if income all is good`() {
        val request = CreditApplication(
            createdAt = LocalDateTime.now(),
            totalAmount = 10,
            monthlyPayment = 1,
            user = createUser(income = 1000000)
        )
        val result = scoreController.simpleScore(request)
        Assertions.assertTrue(result)
    }
    fun `do not issue a loan if user already banned`() {
        val request = CreditApplication(
            createdAt = LocalDateTime.now(),
            totalAmount = 10,
            monthlyPayment = 1,
            user = createUser(name = "user1")
        )
        val result = scoreController.simpleScore(request)
        Assertions.assertFalse(result)
    }


}
