package demo.application.controller

import org.springframework.stereotype.Component


@Component
class LogicService {
    fun canGet(creditApplication: CreditApplication): Boolean {
        if (creditApplication.user.age < 18) return false;
        if (creditApplication.user.loans.filter { it.isClose == false }.sumOf { it.monthlyPayment } +
            creditApplication.monthlyPayment > creditApplication.user.income * 0.3) return false;
        if (creditApplication.user.name == "Jack") return false;
        if (creditApplication.totalAmount / creditApplication.monthlyPayment / 12 + creditApplication.user.age > 60)
            return false;
        return true;
    }
}