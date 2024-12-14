package demo.application.controller

import org.springframework.stereotype.Component

@Component
class LogicService {
    fun simpleScore(creditApplication: CreditApplication): Boolean {
        // Нельзя выдавать кредит клиентам не достигшим 18 лет
        if (creditApplication.user.age < 18) return false;
        // Если суммарный месячный платеж составляет больше трети дохода то нельзя выдавать новый кредит
        if (creditApplication.user.loans.filter { it.isClose == false }.sumOf { it.monthlyPayment } +
            creditApplication.monthlyPayment > creditApplication.user.income * 0.3) return false;
        // Нельзя выдавать кредит женщинам, если его выплата не закончится, когда её исполнится 60 лет и аналогично мужчинам, если выплата не закончится, когда ему исполнится 60 лет
        if ((creditApplication.totalAmount / creditApplication.monthlyPayment / 12 + creditApplication.user.age > 65 and creditApplication.user.gender == "male") or (creditApplication.totalAmount / creditApplication.monthlyPayment / 12 + creditApplication.user.age > 60 and creditApplication.user.gender == "female"))
            return false;
        return true;
    }
}