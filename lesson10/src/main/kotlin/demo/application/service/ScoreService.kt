package demo.application.service

import demo.application.dto.CreditApplication
import demo.application.dto.calculateMonthlyPayment
import org.springframework.stereotype.Service

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
@Service
class ScoreService {

    fun simpleScore(creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        if (creditApplication.user.age < 18) {
            return false
        }

        val newMonthlyPayment = creditApplication.user.calculateMonthlyPayment() +
                creditApplication.monthlyPayment

        // Если суммарный месячный платеж составляет больше трети дохода то нельзя выдавать новый кредит
        return newMonthlyPayment <= creditApplication.user.income / 3
    }

}