package demo.application.service

import demo.application.dto.CreditApplication
import org.springframework.stereotype.Service

@Service
class ScoreService(val pdnService: PdnService) {
    fun run(creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        if (creditApplication.user.age <= 18) {
            return false
        }

        // Если суммарный месячный платеж составляет больше трети дохода то нельзя выдавать новый
        // кредит
        return pdnService.run(
                creditApplication.user,
                creditApplication.correspondingLoan.monthlyPayment
        ) > 0
    }
}
