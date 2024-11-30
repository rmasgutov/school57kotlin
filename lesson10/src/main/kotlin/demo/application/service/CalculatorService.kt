package demo.application.service

import demo.application.client.CrmClient
import demo.application.dto.calculateMonthlyPayment
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

        // максимальный месячный платеж не должен превышать треть дохода
        return max(user.income / 3 - user.calculateMonthlyPayment(), 0) // ну вдруг...
    }

}