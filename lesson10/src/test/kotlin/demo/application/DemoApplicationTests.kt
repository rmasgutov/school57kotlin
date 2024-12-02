package demo.application

import demo.application.controller.CalculatorController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("dev")
class DemoApplicationTests {

    @Autowired
    lateinit var calculatorController: CalculatorController

    @Test
    fun someTest() {
        // вроде чето покрывает
        calculatorController.simpleScore(userId = "123", monthlyPayment = 213)

    }
}
