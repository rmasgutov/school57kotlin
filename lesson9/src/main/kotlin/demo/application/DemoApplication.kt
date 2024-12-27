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
class DemoApplicationTests { @Autowired
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

		Assertions.assertFalse(scoreController.simpleScore(creditApplication), "Loan shouldn't be approved for users under 18 years old")
	}

	@Test
	fun `should approve if user has no loans and total payment doesn't exceed a third of income`() {
		val creditApplication = CreditApplication(
			testLoan.copy(monthlyPayment = 0),
			testUser,
		)

		Assertions.assertTrue(
			scoreController.simpleScore(creditApplication),
			"Loan should be approved for users with no loans and whose total payment doesn't exceed a third of income"
		)
	}

	@Test
	fun `should not approve if user has no loans and total payment exceeds a third of income`() {
		val creditApplication = CreditApplication(
			testLoan.copy(monthlyPayment = 1e9.toLong()),
			testUser,
		)

		Assertions.assertFalse(
			scoreController.simpleScore(creditApplication),
			"Loan shouldn't be approved for users with no loans and whose total payment exceeds a third of income"
		)
	}

	@Test
	fun `should approve if user has loans and total payment doesn't exceed a third of income`() {
		val creditApplication = CreditApplication(
			testLoan.copy(monthlyPayment = 0),
			testUser.copy(loans = listOf(testLoan)),
		)

		Assertions.assertTrue(
			scoreController.simpleScore(creditApplication),
			"Loan should be approved for users with loans and whose total payment doesn't exceed a third of income"
		)
	}

	@Test
	fun `should not approve if user has loans and total payment exceeds a third of income`() {
		val creditApplication = CreditApplication(
			testLoan.copy(monthlyPayment = 1e9.toLong()),
			testUser.copy(loans = listOf(testLoan)),
		)

		Assertions.assertFalse(
			scoreController.simpleScore(creditApplication),
			"Loan shouldn't be approved for users with loans and whose total payment exceeds a third of income"
		)
	}

	@Test
	fun `should approve if user has closed loans and total payment doesn't exceed a third of income`() {
		val creditApplication = CreditApplication(
			testLoan.copy(monthlyPayment = 0),
			testUser.copy(loans = listOf(testLoan.copy(isClosed = true, monthlyPayment = 1e9.toLong())))
		)

		Assertions.assertTrue(
			scoreController.simpleScore(creditApplication),
			"Loan should be approved for users with closed loans and whose total payment doesn't exceed a third of income"
		)
	}

	@Test
	fun `should not approve if user has closed loans and total payment exceeds a third of income`() {
		val creditApplication = CreditApplication(
			testLoan.copy(monthlyPayment = 1e9.toLong()),
			testUser.copy(loans = listOf(testLoan.copy(isClosed = true, monthlyPayment = 1e9.toLong())))
		)

		Assertions.assertFalse(
			scoreController.simpleScore(creditApplication),
			"Loan shouldn't be approved for users with closed loans and whose total payment exceeds a third of income"
		)
	}

}