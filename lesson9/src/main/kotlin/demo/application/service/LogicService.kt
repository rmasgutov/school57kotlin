package demo.application.service

import demo.application.dto.CreditApplication

class LogicService {
    fun simpleScore(creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        if (creditApplication.user.age <  18 ) {
            return false
        }

        val existingLoans = creditApplication.user.loans.filter { !it.isClosed }

        // Если суммарный месячный платеж составляет больше трети дохода то нельзя выдавать новый кредит
        val totalMonthlyPayment = existingLoans.sumOf { it.monthlyPayment } + creditApplication.user.income

        if (totalMonthlyPayment <= creditApplication.user.income / 3) return true
        return false
    }
}