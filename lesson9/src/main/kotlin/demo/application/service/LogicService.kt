package demo.application.service

import demo.application.dto.CreditApplication
import demo.application.dto.Loan
import org.springframework.stereotype.Component

@Component
class LogicService {
    fun simpleScore(creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам, не достигшим 18 лет
        if (creditApplication.user.age < 18) {
            return false
        }

        val openedLoans = creditApplication.user.loans.filterNot(Loan::isClosed)

        val totalMonthlyPayment =
                openedLoans.sumOf(Loan::monthlyPayment) +
                        creditApplication.correspondingLoan.monthlyPayment

        // Если суммарный месячный платеж составляет больше трети дохода то, нельзя выдавать новый
        // кредит
        return totalMonthlyPayment <= creditApplication.user.income / 3
    }
}
