package demo.application.service

import demo.application.client.CrmClient
import demo.application.util.SyntheticUserGenerator
import org.springframework.stereotype.Service
import kotlin.math.max

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
@Service
class CalculatorService(
    private val crmClient: CrmClient,
    private val syntheticUserGenerator: SyntheticUserGenerator,
    private val pdnService: PDNService,
) {

    fun calculate(userId: String): Long {
        val user = if (System.getenv("environment") == "Test") {
            syntheticUserGenerator.generateUser()
        } else {
            crmClient.getUserById(userId)
        }

        if (user.age < 18) {
            return 0
        }

        val maxMonthlyPayment = pdnService.calculateMaxMonthlyPayment(user)
        val currentMonthlyPayment = user.calculateMonthlyPayment()

        // максимальный месячный платеж не должен превышать треть дохода
        return max(maxMonthlyPayment - currentMonthlyPayment, 0) // ну вдруг...
    }

}