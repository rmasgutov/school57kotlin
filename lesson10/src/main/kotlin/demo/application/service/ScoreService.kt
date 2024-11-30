package demo.application.service

import demo.application.dto.CreditApplication
import org.springframework.stereotype.Service

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
@Service
class ScoreService(
    private val pdnService: PDNService
) {

    fun simpleScore(creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        if (creditApplication.user.age < 18) {
            return false
        }

        val newMonthlyPayment = creditApplication.user.calculateMonthlyPayment() +
                creditApplication.monthlyPayment

        // Если суммарный месячный платеж составляет больше трети дохода то нельзя выдавать новый кредит
        return newMonthlyPayment <= pdnService.calculateMaxMonthlyPayment(creditApplication.user)
    }

}