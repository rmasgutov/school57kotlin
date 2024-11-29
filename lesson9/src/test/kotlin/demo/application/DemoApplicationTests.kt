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
                        // Иван учится на 1 курсе магистратуры, параллельно работая
                        User(
                                age = 23,
                                name = "Ivan",
                                User.Sex.MALE,
                                income = 100_000,
                                loans = listOf<Loan>()
                        ),
                        // Иван хочет приобрести себе автомобиль
                        Loan(
                                amount = 1_000_000,
                                monthlyPayment = 10_000,
                        )
                )
        val result = scoreController.simpleScore(request)
        // Ивану одобрят кредит
        Assertions.assertTrue(result)
    }

    @Test
    fun underagedTest() {
        val request =
                CreditApplication(
                        // Василий только оканчивает школу, но уже подрабатывает
                        User(
                                age = 17,
                                name = "Vasiliy",
                                User.Sex.MALE,
                                income = 10_000,
                                loans = listOf<Loan>()
                        ),
                        // Василий хочет купить себе ноутбук для учёбы
                        Loan(amount = 100_000, monthlyPayment = 1_000)
                )

        val result = scoreController.simpleScore(request)
        // К сожалению, Василию ещё нет 18 лет, поэтому кредит ему не одобрят
        Assertions.assertFalse(result)
    }

    @Test
    fun lowIncomeTest() {
        val request =
                CreditApplication(
                        // Анна только недавно начала 1 курс бакалавриата, живёт на стипендию
                        User(
                                age = 18,
                                name = "Anna",
                                User.Sex.FEMALE,
                                income = 1967,
                                loans = listOf<Loan>()
                        ),
                        // Анна хочет купить себе электросамокат, чтобы не опаздывать
                        Loan(amount = 20_000, monthlyPayment = 1_000)
                )
        val result = scoreController.simpleScore(request)
        // К сожалению, месячный доход Анны невелик. Кредит ей не одобрят
        Assertions.assertFalse(result)
    }
}
