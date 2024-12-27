package demo.application.servicelogic

import demo.application.dto.CreditApplication
import org.springframework.stereotype.Service

@Service
class ScoreService {

    fun simpleScore(creditApplication: CreditApplication): Boolean {
        if (creditApplication.user.age < 18) {
            return false
        }

        val existingLoans = creditApplication.user.loans.filterNot {
            it.isClosed
        }
        println(existingLoans)
        val totalMonthlyPayment =
            existingLoans.sumOf { it.monthlyPayment } + creditApplication.requestedLoan.monthlyPayment
        println(totalMonthlyPayment)
        return true
    }
}