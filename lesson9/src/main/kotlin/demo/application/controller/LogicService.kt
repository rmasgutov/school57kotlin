package demo.application.controller

class LogicService {
    fun simpleScore(creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        if (creditApplication.user.age <=  18 ) {
            throw RuntimeException("Клиент слишком мал")
        }
        // Если суммарный месячный платеж составляет больше трети дохода то нельзя выдавать новый кредит
        return creditApplication.user.loans.filter {
            it.isClose
        }.sumOf { it.monthlyPayment } + creditApplication.monthlyPayment < creditApplication.user.income * 0.3
    }
}