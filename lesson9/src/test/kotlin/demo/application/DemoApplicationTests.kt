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

	@Autowired lateinit var scoreController: ScoreController

	@Test
	fun successfulTest() {
		val request =
			CreditApplication(
				//Мухамед - 93 летний дед предприниматель
				User(
					age = 93,
					name = "Muhamed",
					User.Sex.MALE,
					income = 1000000,
					loans = listOf<Loan>()
				),
				//Мухамед посчитал отличной идеей купить себе самолет в таком возрасте
				Loan(
					amount = 10000000,
					monthlyPayment = 100000,
				)
			)
		val result = scoreController.simpleScore(request)
		//Мы конечно же рады помочь дедушке
		Assertions.assertTrue(result)
	}
	@Test
	fun underagedTest() {
		val request =
			CreditApplication(
				//Адольф - 13 летний австрийский мальчик, подрабатывающий художником
				User(
					age = 13,
					name = "Adolf",
					User.Sex.MALE,
					income = 10000,
					loans = listOf<Loan>()
				),
				//Адольф очень хочет купить себе скин из кски драгон лор, для чего ему нужны деньги.
				Loan(amount = 100000, monthlyPayment = 1000)
			)

		val result = scoreController.simpleScore(request)
		//Адольф еще мелковат для таких действий, кредита ему не видать
		Assertions.assertFalse(result)
	}
	@Test
	fun lowIncomeTest() {
		val request =
			CreditApplication(
				//Валера - рабочий завода с низкой зп, зато с широкой душой и широким пузом
				User(
					age = 45,
					name = "Valera",
					User.Sex.MALE,
					income = 2000,
					loans = listOf<Loan>()
				),
				//Валера хочет купить телик перед чемпионатом мира по футболу
				Loan(amount = 30000, monthlyPayment = 2000)
			)
		val result = scoreController.simpleScore(request)
		//Валере б подзаработать, а то и кушать нечего будет. Никакого кредита
		Assertions.assertFalse(result)
	}
}