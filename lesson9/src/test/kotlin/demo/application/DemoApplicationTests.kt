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
			createdAt = LocalDateTime.now(),
			totalAmount = 100000,
			monthlyPayment = 10000,
			user = User(age = 30, name = "Victor", sex = 1, income = 200000, loans = listOf(User.Loan(LocalDateTime.now(), true, 10000)))
		)
		val result = scoreController.simpleScore(request)
		Assertions.assertEquals(true, result)
	}

	@Test
	fun Test2() {
		val request = CreditApplication(
			createdAt = LocalDateTime.now(),
			totalAmount = 20000000000000000,
			monthlyPayment = 10000,
			user = User(age = 16, name = "Masha", sex = 2, income = 100000000000, loans = listOf(User.Loan(LocalDateTime.of(2022, 10, 12, 10, 30
			), false, 100000)))
		)
		val result = scoreController.simpleScore(request)
		Assertions.assertEquals(false, result)
	}
}
