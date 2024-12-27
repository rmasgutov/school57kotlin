package demo.application.controller

import org.springframework.boot.Banner

class LogicService {
    fun simpleScore(creditApplication: CreditApplication): Boolean {
        val banned = listOf<String>("user1", "user2", "user3")
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        if (creditApplication.user.age <=  18 ) {
            return false
        }
        if (creditApplication.user.name in banned) {
            return false
        }
        // Если суммарный месячный платеж составляет больше трети дохода то нельзя выдавать новый кредит
        return creditApplication.user.loans.filter {
            it.isClose
        }.sumOf { it.monthlyPayment } + creditApplication.monthlyPayment < creditApplication.user.income * 0.3
    }
}