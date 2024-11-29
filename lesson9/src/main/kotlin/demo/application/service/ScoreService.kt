package demo.application.service

import demo.application.dto.CreditApplication
import org.springframework.stereotype.Service

@Service
class ScoreService {

    fun simpleScore(creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        if (creditApplication.user.age < 18) {
            return false
        }

        val existingLoans = creditApplication.user.loans.filterNot {
            it.isClosed
        }

        println(existingLoans)

        val totalMonthlyPayment = existingLoans.sumOf { it.monthlyPayment } + creditApplication.requestedLoan.monthlyPayment

        println(totalMonthlyPayment)

        // Кредит выдаем только если сумма ежемесячных платежей всех кредитов не превышает 1/3 дохода клиента
        return totalMonthlyPayment <= creditApplication.user.income / 3
    }

}