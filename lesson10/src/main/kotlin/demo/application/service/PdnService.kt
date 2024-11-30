package demo.application.service

import demo.application.dto.Loan
import demo.application.dto.User
import org.springframework.stereotype.Service

@Service
class PdnService {
    fun run(user: User, monthlyPayment: Long): Long {
        val existingMonflyPayment = user.loans.filterNot(Loan::isClosed).sumOf(Loan::monthlyPayment)

        val totalMonthlyPayment = existingMonflyPayment + monthlyPayment

        // Если суммарный месячный платеж не может составлять больше трети дохода
        return totalMonthlyPayment / 3 - user.income
    }
}
