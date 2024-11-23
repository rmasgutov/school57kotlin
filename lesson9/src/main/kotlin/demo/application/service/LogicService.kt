package demo.application.service

import demo.application.dto.CreditApplication
import demo.application.dto.Loan
import org.springframework.stereotype.Component

@Component
class LogicService {
    fun simpleScore(creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        return creditApplication.user.age >= 18 &&
                // Если суммарный месячный платеж составляет больше трети дохода то нельзя выдавать
                // новый кредит
                creditApplication.user.loans.filterNot(Loan::isClosed).sumOf(Loan::monthlyPayment) +
                        creditApplication.correspondingLoan.monthlyPayment <=
                        creditApplication.user.income / 3
    }
}
