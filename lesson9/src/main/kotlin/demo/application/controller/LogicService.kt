package demo.application.controller

import org.springframework.stereotype.Service

@Service
class LogicService {

    fun simpleScore(creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        if (creditApplication.user.age < 18) {
            throw RuntimeException("Клиент слишком мал")
        }

        val existingLoans = creditApplication.user.loans.filter {
            !it.isClosed
        }

        val totalMonthlyPayment = existingLoans.sumOf { it.monthlyPayment } + creditApplication.monthlyPayment

        // Кредит выдаем только если сумма всех ежемесячных платежей не превышает 1/3 дохода клиента
        return totalMonthlyPayment <= creditApplication.user.income / 3
    }

}